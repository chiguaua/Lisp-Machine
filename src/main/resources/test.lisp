
(setf x 10)
(setf y "Hello")
(setf z (+ x 5))
(print z)
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

(setq x 10)
(print x)

(let ((input (read)))  (print input))