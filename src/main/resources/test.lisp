
(let ((input (read)))  (print input))

(@call-java System.out.println("CALL JAVA FROM LISP"))

(do ((i 0 (+ i 1))
     (j 10 (- j 2)))
    ((>= i 5) 'done)
  (print (list i j))
    (print (list j i)))