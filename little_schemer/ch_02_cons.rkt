#lang plai

;; firsts
(define firsts
  (lambda (l)
    (cond
      ((null? l)'())
      ( else ( cons (car(car l)) (firsts (cdr l)) ) ) )))

;; test firsts
(test (firsts '((a b)(c d)(e f))) '(a c e))

;; rember
(define rember
  (lambda (a lat)
    (cond 
      [(null? lat) '()]
      [(eq? a (car lat)) (cdr lat)]
      [else ( cons (car lat) (rember a (cdr lat) ) )] )))

;; test rember
(define rember_good? (equal? (rember 'b '(a b c d e b) ) '(a c d e b)) )
(test rember_good? #t)

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

;; test insert-l
(test (insert-l 'bannana 'jelly '(peanut butter and jelly))'(peanut butter and bannana jelly))

;; subst
(define (subst old new lat)
  (cond
    [(null? lat) '()]
    [else
     (cond
       [(eq? (car lat) old)(cons new (cdr lat))]
       [else (cons (car lat) (subst (old new (cdr lat))))])]))

;; test subst
(define (subst_good?)
  (equal? (subst 'a 'z '(a b c a)) '(z b c a)))
(test (subst_good?) #t)

;; subst_2
(define (subst_2 o1 o2 new lst)
  (cond 
    [(null? lst) '()]
    [else
     (cond
       [(or 
         (eq? o1 (car lst))
         (eq? o2 (car lst))) 
        (cons new (cdr lst))]
       [else (cons (car lst)(subst_2 o1 o2 new (cdr lst)))])]))

;; test subst_2
(define (subst_2_good?)
  (equal? (subst_2 'c 'b 'z '(a b c b))'(a z c b)))
(test (subst_2_good?)#t)

;; multirember
(define multirember 
  (lambda (a lat)
    (cond
      ((null? lat)'())
      (else
       (cond
         ((eq? (car lat) a)(multirember a (cdr lat)))
         (else (cons (car lat)(multirember a (cdr lat)))))))))

;; test multirember
(define (multi_rember_good?)
  (equal? (multirember 'a '(a b a b a))'(b b)))
(test (multi_rember_good?)#t)

;; multi_insert_r
(define (multi_insert_r old new lst)
  (cond
    [(null? lst) '()]
    [else 
     (cond
       [(eq? old (car lst))
        (cons old (cons new(multi_insert_r old new (cdr lst))))]
       [else 
        (cons (car lst)(multi_insert_r old new (cdr lst)))])]))

;; test multi_insert_r
(define (multi_insert_r_good?)
  (equal? (multi_insert_r 'a 'z '(a b c a))'(a z b c a z)))
(test (multi_insert_r_good?) #t)

;; multi_insert_l
(define (multi_insert_l old new lst)
  (cond
    [(null? lst) '()]
    [else
     (cond
       [(eq? old (car lst))
        (cons new(cons old(multi_insert_l old new (cdr lst))))]
       [else (cons (car lst)(multi_insert_l old new (cdr lst)))])]))

;; test multi_insert_l
(define (multi_insert_l_good?)
  (equal? (multi_insert_l 'a 'z '(a b c a)) '(z a b c z a)))
(test (multi_insert_l_good?) #t)
;; multi_subst
(define (multi_subst old new lst)
  (cond
    [(null? lst) '()]
    [else 
     (cond
       [(eq? old (car lst))
        (cons new (multi_subst old new (cdr lst)))]
       [else (cons (car lst)(multi_subst old new (cdr lst)))])]))

;; test multi_subst
(define (multi_subst_good?)
  (equal? (multi_subst 'a 'z '(a b c a))'(z b c z)))
(test (multi_subst_good?) #t)