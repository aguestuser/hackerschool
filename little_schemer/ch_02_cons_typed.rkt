#lang plai-typed

;; rember
(define (rember [a : 'a] [lst : (listof 'a)]) : (listof 'a) 
  (cond 
    [(empty? lst) empty]
    [(eq? a (first lst)) (rest lst)]
    [else ( cons (first lst) (rember a (rest lst)))]))

;; test rember
(define rember_good? 
  (equal? (rember "b" (list "a" "b" "c" "d" "e" "b")) (list "a" "c" "d" "e" "b" )))
(test rember_good? #t)

;; rember_λ (with long-form signature and func delc)
(define rember_λ : ('a (listof 'a) -> (listof 'a))
  (lambda (a lst) 
    (cond 
      [(empty? lst) empty]
      [(eq? a (first lst)) (rest lst)]
      [else ( cons (first lst) (rember_λ a (rest lst)))])))

;; test rember_λ
(define rember_λ_good? 
  (equal? (rember_λ "b" (list "a" "b" "c" "d" "e" "b")) (list "a" "c" "d" "e" "b" )))
(test rember_λ_good? #t)
