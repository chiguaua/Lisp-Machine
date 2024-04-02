

(defun aAdd (a b)
  (+ (+ a b) a))


(defun subtract (a b)
  (- a b))

(defun multiply (a b)
  (* a b))

(defun divide (a b)
  (/ a b))

(defun factorial (a)
  (if (> a 1)
  (* a (factorial (- a 1)))
  1
  )
)



(print(list 1 2 3 4))

((lambda (x) (* x x))  5)

(add 3 1)
(subtract 10 4)
(multiply 2 6)
(divide 8 2)
(print "dddqddq")

( let (( x 10)
        (y 5))
     (print (add x y))
     )

(print (or T NIL))
(print (and (> 4 2) (= 3 1)))
(print (factorial 5))
(print (and (> 5 3) (< 7 10)))
(print (or (< 5 3) (> 7 10)))
(print (not (= 5 5)))
(print (rem 10 3))
(print (mod 10 3))

(let ((input (read)))  (print input))

(@call-java System.out.println("CALL JAVA FROM LISP"))


(setq a 1
      b 2)

(let (( x 4))
(cond
((= x 1) (print "1"))
((= x 2) (print "2"))
((= x 3) (print "3"))
(T (print "S"))
)
)

(let ((input (read)))  (print input))

(@call-java System.out.println("CALL JAVA FROM LISP"))

(loop for i from 1 to 5
      do (print i)
        (print (+ 1 i))
      )


(let ((lst (list 1 2 3 4)))
  (print (car lst))
  (print (cdr lst))
  (print (cons 0 lst)))