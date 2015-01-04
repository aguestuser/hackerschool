#lang plai

;; rember

(define rember
  (lambda (a lat)
    (cond 
      [(null? lat) '()]
      [(eq? a (car lat)) (cdr lat)]
      [else ( cons (car lat) (rember a (cdr lat) ) )] )))

;; test rember
(define rember_good? 
  (eq? (rember 'b '(a b c d e b) ) '(a c d e b)) )
(test rember_good? #t)

;; firsts

(define firsts
  (lambda (l)
    (cond
      ((null? l)'())
      ( else ( cons (car(car l)) (firsts (cdr l)) ) ) )))

;; tests for firsts
(test (firsts '((a b)(c d)(e f))) '(a c e))

;; insert-r

(define insert-r
  (lambda (new old lat)
    (cond 
      [(null? lat) '()]
      [else(cond
             [(eq? old (car lat)) (cons old (cons new (cdr lat)))]
             [ else( cons(car lat) (insert-r new old (cdr lat)) )])])))

;; test insert-r

(test (insert-r 'bannana 'jelly '(peanut butter and jelly)) '(peanut butter and jelly bannana))

;; insert-l

(define insert-l
  (lambda(new old lat)
    (cond
      [(null? lat)'() ]
      [else (cond
              [ (eq? old (car lat)) (cons new (cons old (cdr lat)))]
              [else (cons (car lat) (insert-l new old (cdr lat)))])])))

;; tests for insert-l

(test (insert-l 'bannana 'jelly '(peanut butter and jelly))'(peanut butter and bannana jelly))


;; multirember

(define multirember 
  (lambda (a lat)
    (cond
      ((null? lat)'())
      (else 
       (cond 
         ((eq? (car lat) a)(multirember a (cdr lat)))
         (else (cons (car lat)(multirember a (cdr lat)))))))))

