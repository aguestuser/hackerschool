#lang plai-typed

(define-type ArithC
  [numC (n : number)]
  [plusC (l : ArithC) (r : ArithC)]
  [multC (l : ArithC) (r : ArithC)])

(define (interp [a : ArithC]) : number
  (type-case ArithC a
    [numC (n) n]
    [plusC (l r) (+ (interp l) (interp r))]
    [multC (l r) (* (interp l) (interp r))]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; tests for ArithC interpreter ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; test each case individually ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; test numC
(define num : ArithC (numC 3))
(test (interp num) 3)

;; test plusC
(define sum : ArithC (plusC (numC 2) (numC 3)))
(test (interp sum) 5)

;; test multC
(define prod : ArithC (multC (numC 2) (numC 3)))
(test (interp prod) 6)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; test with pattern matching ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define a_num : ArithC (numC 3))
(define a_sum : ArithC (plusC (numC 2) (numC 3)))
(define a_prod : ArithC (multC (numC 2) (numC 3)))

(define (good? [ac : ArithC]) : boolean
  (type-case ArithC ac
    [numC (n) (= (interp ac) 3)]
    [plusC (l r) (= (interp ac) 5)]
    [multC (l r) (= (interp ac) 6)]))

(test(good? a_num) #t)
(test(good? a_sum) #t)
(test(good? a_prod) #t)