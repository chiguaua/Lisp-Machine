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

(print (or true false))
(print (and (> 4 2) (= 3 1)))
(print (factorial 5))
(print (and (> 5 3) (< 7 10)))
(print (or (< 5 3) (> 7 10)))
(print (not (= 5 5)))
(print (rem 10 3))
(print (mod 10 3))
