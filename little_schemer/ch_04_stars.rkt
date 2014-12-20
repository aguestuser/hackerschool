#lang racket

(require rackunit)

;; atom?
(define (atom? x)
  (and (not (pair? x)) (not (null? x))))

;; rember*
(define rember* 
  (lambda (a l)
    (cond
     [(null? l) '()]
     [(atom? (car l))
      (cond
       [(eq? (car l) a) (rember* a (cdr l))]
       [else (cons (car l) (rember* a (cdr l)))])]
     [else (cons (rember* a (car l)) (rember* a (cdr l)) )])))

;; rember* tests
(module+ test
  (check-equal? 
   (rember* 'cup '((coffee) cup ((tea) cup)(and (hick)) cup))
   '((coffee)((tea))(and(hick)))))
 

;; insertR*
(define insertR*
  (lambda(new old l)
    (cond
     [(null? l) '()]
     [(atom? (car l))
      (cond
       [(eq? (car l) old)(cons old (cons new (insertR* new old (cdr l))))]
       [else (cons (car l) (insertR* new old (cdr l)))])]
     [else (cons (insertR* new old (car l)) (insertR* new old (cdr l)))])))

;; test insertR*
(module+ test
  (check-equal?
   (insertR* 'roast 'chuck '((how much (wood)) 
                            could ((a (wood) chuck)) 
                            (((chuck)))
                            (if (a) ((wood chuck))) 
                            could chuck wood))
   '((how much (wood))
     could ((a (wood) chuck roast)) 
     (((chuck roast)))
     (if (a) ((wood chuck roast))) 
     could chuck roast wood)))

;; occur*
(define occur*
  (lambda(a l)
   (cond
    [(null? l) 0]
    [(atom? (car l))
     (cond 
      [(eq? (car l) a)(+ 1 (occur* a (cdr l)))]
      [else (occur* a (cdr l))])]
    [else (+ (occur* a (car l))(occur* a (cdr l)))])))

;; test occur*
(module+ test
  (check-equal?
   (occur* 'banana '((banana)
                     (split ((((banana ice)))
                     (cream (banana))
                     sherbet)) (banana)
                     (bread)
                     (banana brandy)))
   5))

;; subst*
(define subst*
  (lambda (new old l)
    (cond
     [(null? l) '()]
     [(atom? (car l)) 
      (cond
       [(eq? old (car l)) (cons new (subst* new old (cdr l)))]
       [else (cons (car l) (subst* new old (cdr l)))])]
     [else (cons (subst* new old (car l)) (subst* new old (cdr l)))])))

;; test subst*
(module+ test
  (check-equal?
   (subst* 'orange 'banana '((banana)
                             (split ((((banana ice))) 
                                     (cream (banana))
                                     sherbet)) 
                             (banana)
                             (bread)
                             (banana brandy)))
   '((orange)
     (split ((((orange ice)))
             (cream (orange))
             sherbet)) 
     (orange)
     (bread)
     (orange brandy))))

;; member*
(define member*
  (lambda (a l)
    (cond
     [(null? l) #f]
     [(atom? (car l))
      (or (eq? a (car l))
          (member* a (cdr l)))]
     [else (or (member* a (car l))
               (member* a (cdr l)))])))

;; test member*
(module+ test
  (check-equal? 
   (member* 'chips '((potato) (chips ((with) fish) (chips))))
   #t))

