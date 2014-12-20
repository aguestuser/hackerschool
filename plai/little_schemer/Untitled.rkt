#lang racket

(define multirember 
  (lambda (a lat)
    (cond
      ((null? lat)'())
      (else 
       (cond 
         ((eq? (car lat) a)(multirember a (cdr lat)))
         (else (cons (car lat)(multirember a (cdr lat)))))))))