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

(add 5 3)
(subtract 10 4)
(multiply 2 6)
(divide 8 2)
(print "dddqddq")

( let (( x 10)
        (y 5))
     (print x y)
     )


(print (and (> 5 3) (< 7 10)))
(print (or (< 5 3) (> 7 10)))
(print (not (= 5 5)))

(print (rem 10 3))
(print (mod 10 3))


(defmacro square (x)
  `(* ,x ,x))

(defmacro double (x)
  `(+ ,x ,x))


(print (square 3))
(print (double 4))