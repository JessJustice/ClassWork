{-
Jessica Crow
CS 262
Lab 7

1.
foldl  (((* 6 5)*3)*8)
foldr  (6*(5*(3*8)))
-}

module Lab7
(length'
, convertIntToStringLeft
, convertIntToStringRight
, functA
, functB
, functC
, functD
, mystery
) where

import Data.List
import Data.Char


--2. 
length' :: Num a => [a] -> a
length' [] = 0
length' xs = foldr (\_ -> (+1)) 0 xs
-- length' = foldl (\acc x -> 1+ acc) 0 [1..10]
-- length' = foldr (\x acc -> 1 + acc) 0 [109..200]



--3.

convertIntToStringLeft :: [Int] -> [Char]
convertIntToStringLeft xs = foldl (\acc x -> acc ++ ( intToDigit x) : []) [] xs 
-- convertIntToStringLeft = foldl (\acc x -> acc ++ [intToDigit x]) []

convertIntToStringRight :: [Int] -> [Char]
convertIntToStringRight  = foldr (\x acc -> intToDigit x : acc) [] 


--4.

functA =  length $ filter (<20) [1..100]
--19

functB = take 10 $ cycle $ filter (>5) $ map (*2) [1..10]
--[6,8,10,12,14,16,18,20,6,8]

functC = sum $ map length $ zipWith (flip (++)) ["love you", "love me"] ["i", "you"]
--19

functD = sum $ map sin [sqrt(x) | x <- [1..100]]
--functD = sum $ map (sin . sqrt . abs) [1..100] 
--POINT FREE FORM inputs from the right and works backwards
--15.214312953439046

--5.
mystery n x = scanr (\y acc -> (acc + y/acc)/2) 1 (replicate n x)
-- this function converges to the square root of the second input. 