{- 
    Lab 8 : Transform to HTML

            Read a file with minimal formatting information
            (inspired by Markdown) and transform it to a 
            valid HTML file with the given formatting.

    Compile from OS terminal or command prompt (not confirmed on Windows yet):
        
        ghc --make -o Lab8 Lab8

    Run:
        
        Linux/Mac:
            ./Lab8 input.md output.html

        Windows (not confirmed yet):
            Lab8.exe input.md output.html
-}

module Main
(
convertBody,
fixSymbol,
findOther,
findOtherS,
dblUndrscr,
dblStar,
sglUndrscr,
sglStar,
lines,
convertToHTML,
hashTags,
main
) where


import System.Environment (getArgs)
import System.IO
import Data.Char

convertBody :: String -> String
convertBody xs = sglStar $ sglUndrscr $ dblUndrscr $ dblStar $ fixSymbol  xs


convertToHTML :: [[Char]] -> [Char]
convertToHTML [] = []
convertToHTML xs = ("<!DOCTYPE html>\n<html> \n<head>\n" 
           ++ "    <meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />\n" 
           ++ "    <title>" ++ head xs ++ "</title> \n</head>\n" 
		   ++ "<body>\n" ++   convertBody ( unlines( tail xs)) 
		   ++ "</body>\n</html>\n")


hashTags :: [[Char]] -> [[Char]]
hashTags [] = []
hashTags (x:xs)
   | take 2 ( x) == "##" =  ("<h2>" ++ (drop 2 (x)) ++ "</h2>"):(hashTags xs)
   | take 1 ( x) == "#" =  ("<h1>" ++ (drop 1 (x)) ++ "</h1>"):(hashTags xs)
   | take 3 ( x) == "---" = ("<hr>") :(hashTags xs)
   | otherwise = x:(hashTags xs)


fixSymbol :: [Char] -> [Char]
fixSymbol [] = []
fixSymbol (x:xs)
    | x == '&' = '&':'a':'m':'p':(fixSymbol xs)
    | x == '<' = '&':'l':'t':(fixSymbol xs)
	| otherwise = x:(fixSymbol xs)


dblUndrscr :: [Char] -> [Char]
dblUndrscr [] = []
dblUndrscr(x:xs)
   | x == '_' && head xs == '_' =  findOther $ "<strong>" ++ (drop 1( xs))
   | otherwise = x:(dblUndrscr xs)
   
dblStar :: [Char] -> [Char]
dblStar [] = []
dblStar(x:xs) 
   | x == '*' && head xs == '*' =  findOther $ "<strong>" ++ (drop 1( xs))
   | otherwise = x:(dblStar xs)
 
sglUndrscr :: [Char] -> [Char]
sglUndrscr [] = []
sglUndrscr(x:xs)
   | x == '_'                   =  findOtherS $ "<em>" ++ ( xs)
   | otherwise = x:(sglUndrscr xs) 
   
sglStar :: [Char] -> [Char]
sglStar [] = []
sglStar(x:xs)
   | x == '*'                   =  findOtherS $ "<em>" ++ ( xs)
   | otherwise = x:(sglStar xs) 
   
findOther :: [Char] -> [Char]
findOther [] = [] 
findOther (x:xs)
   | x == '_'  =   "</strong>" ++ (dropWhile (== '_') xs)
   | x == '*'  =   "</strong>" ++ (dropWhile (== '*') xs)
   | otherwise = x:(findOther xs)

findOtherS :: [Char] -> [Char]
findOtherS [] = [] 
findOtherS (x:xs)
   | x == '_'  =   "</em>" ++ ( xs)
   | x == '*'  =  "</em>" ++ ( xs)
   | otherwise = x:(findOtherS xs)

{-
    Entry point function.  Reads from the command line arguments
    to find the file to process.
-}
main = do
    args <- getArgs                     -- command line args
    let (infile,outfile) = (\(x:y:ys)->(x,y)) args
    putStrLn $ "Input file:  " ++ infile
    putStrLn $ "Output file: " ++ outfile
    contents <- readFile infile
    let setOfLines = lines contents
    writeFile outfile $ unlines $ hashTags $ lines $ convertToHTML setOfLines
