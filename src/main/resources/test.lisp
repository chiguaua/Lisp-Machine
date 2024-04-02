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
