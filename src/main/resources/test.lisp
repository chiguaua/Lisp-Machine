

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
      do (print i))