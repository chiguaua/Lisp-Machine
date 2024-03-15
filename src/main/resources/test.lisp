(defun add (a b)
  (+ a b))

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
  1)
)


((lambda (x) (+ x 10)) 5)




(add 5 3)
(subtract 10 4)
(multiply 2 6)
(divide 8 2)
(print "dddqddq")