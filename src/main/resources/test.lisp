

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

(do ((i 0 (+ i 1))
     (j 10 (- j 2)))
    ((>= i 5) 'done)
  (print (list i j))
    (print (list j i)))

