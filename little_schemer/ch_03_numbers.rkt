#lang plai
;; atom?
(define (atom? x)
  (and (not (pair? x)) (not (null? x))))

;; test atom?
(define (atom_good?)
  (and 
   ;(atom? '())
   (atom? 'a)
   (atom? 1)
   (not (atom? '(a b c)))
   (not (atom? (list 1 2 3)))))
(test (atom_good?) #t)

;; add1
(define (add1 n)
  (+ n 1))

;; sub1
(define (sub1 n)
  (- n 1))

;; .+
(define (.+ n m)
  (cond
    [(zero? m) n]
    [else (add1 (.+ n (sub1 m)))]))

;; test .+
(define (.+_good?)
  (and
   (eq? (.+ 0 0) 0)
   (eq? (.+ 0 1) 1)
   (eq? (.+ 1 1) 2)
   (eq? (.+ 5 5) 10)))
(test (.+_good?) #t)

;; .-
(define (.- n m)
  (cond
    [(zero? m) n]
    [else (sub1 (.- n (sub1 m)))]))

;; test .-
(define (.-_good?)
  (and
   (eq? (.- 5 3) 2)
   (eq? (.- 2 1) 1)
   (eq? (.- 5 0) 5)
   (eq? (.- 0 0) 0)))
(test (.-_good?)#t)

;; add_tup
(define (add_tup tup)
  (cond
    [(null? tup) 0]
    [else (.+ (car tup) (add_tup (cdr tup)))]))

;; test add_tup
(define (add_tup_good?)
  (and
   (eq? (add_tup '(0 1 2)) 3)
   (eq? (add_tup '(3 3 3)) 9)))
(test (add_tup_good?) #t)

;; .<
(define (.< n m)
  (cond
    [(zero? m) #f]
    [(zero? n) #t]
    [else (.< (sub1 n)(sub1 m))]))
;; test .<
(define (.<_good?)
  (and
   (.< 0 1)
   (.< 3 5)
   (not (.< 1 0))
   (not (.< 1 1))))
(test (.<_good?) #t)

;; .=
(define (.= n m)
  (cond
    [(.< n m) #f]
    [(.< m n) #f]
    [else #t]))
;; test .=
(define (.=_good?)
  (and
   (.= 1 1)
   (not (.= 1 0))
   (not (.= 0 1))))
(test (.=_good?) #t)

;; ^
(define (^ n m)
  (cond
    [(zero? m) 1]
    [else (* n (^ n (sub1 m)))]))
;; test ^
(define (^_good?)
  (and 
   (= (^ 5 0) 1)
   (= (^ 5 2) 25)))
(test (^_good?) #t)

;; div
(define (div n m)
  (cond
    [(< n m) 0]
    [else (add1 (div (- n m) m))]))
;; test div
(define (div_good?)
  (and 
   (= (div 6 3) 2)
   (= (div 8 3) 2)))
(test (div_good?) #t)

;; len
(define (len lst)
  (cond
    [(null? lst) 0]
    [else (add1 (len (cdr lst)))]))
;; test len
(define (len_good?)
  (and
   (= (len '()) 0)
   (= (len '(a)) 1)
   (= (len '(a b c)) 3)))
(test (len_good?) #t)

;; pick
(define (pick n lst)
  (cond 
    [(= n 1)(car lst)]
    [else (pick (- n 1) (cdr lst))]))
;; test pick
(define (pick_good?)
  (and
   (eq? (pick 1 '(a b c)) 'a)
   (eq? (pick 2 '(a b c)) 'b)
   (eq? (pick 3 '(a b c)) 'c)))
(test (pick_good?) #t)

;; rempick
(define (rempick n lst)
  (cond 
    [(zero? n) lst]
    [(null? lst) '()]
    [(= n 1) (cdr lst)]
    [else (cons (car lst) (rempick (sub1 n) (cdr lst)))]))
;; test rempick
(define (rempick_good?)
  (and
   (equal? (rempick 1 '(a b c)) '(b c))
   (equal? (rempick 2 '(a b c)) '(a c))
   (equal? (rempick 3 '(a b c)) '(a b))
   (equal? (rempick 0 '(a b c)) '(a b c))
   (equal? (rempick 4 '(a b c)) '(a b c))))
(test (rempick_good?) #t)

;; eqan
(define (eqan a b)
  (cond
    [(and (number? a) (number? b)) (= a b)]
    [(or (number? a) (number? b)) #f]
    [else (eq? a b)]))

;; occur
(define (occur item lst)
  (cond
   [(null? lst) 0]
   [(eq? item (car lst))(add1 (occur item (cdr lst)))]

   [else (occur item (cdr lst))]))
;; test occur
(define (occur_good?)                   
  (and 
   (= (occur 'a '(a b a)) 2)
   (= (occur 'c '(a b a)) 0)))
(test (occur_good?) #t)
