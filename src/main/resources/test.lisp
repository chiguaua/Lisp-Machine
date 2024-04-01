
(let ((input (read)))  (print input))

(@call-java System.out.println("CALL JAVA FROM LISP"))

(defun testCond (x)
  (cond
    ((= x 1) (print "x is equal to 1"))
    ((= x 2) (print "x is equal to 2"))
    ((= x 3) (print "x is equal to 3"))
    (t (print "x is not equal to 1, 2, or 3"))
  ))

(testCond 2)