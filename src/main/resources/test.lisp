(defun checkProgn ()
    (progn
      (print "e")
      (print "9")
      (+ 5 5))
)
(defun factorial (a)
  (if (> a 1)
  (* a (factorial (- a 1)))
  1
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

