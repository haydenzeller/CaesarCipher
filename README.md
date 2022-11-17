# 1214-CaeserCipher
The history of cryptography dates back to the Roman era of Julius Caesar. All communications between his legions
had to be delivered by couriers. If the couriers were captured, the enemy could read Caesar’s orders and the
enemy would be able to take counter measures. To prevent his plans from becoming known to the enemy, Caesar
began to encipher (encode) his dispatches by using what became known as a “Caesar shift”.
The Caesar shift was a very simple, yet, in its time, a very effective encoding scheme. It involved shifting each of
the letters in the dispatch from 1 to 25 characters to the right. Here is how it worked:
Caesar would write his orders in plain Latin (the plaintext) and then give it to his coding clerk. Depending on the
day of the month, the clerk would apply the daily shift value or “key value” (all of Caesar’s legions knew the daily
key values for each day of the month) to each letter in the plaintext. The clerk would then re-write the orders into
the encoded version called the ciphertext.
Suppose the message Caesar wants to send to the Ninth Legion is “ATTACK AT DAWN”.
Here are the letters of the alphabet: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
It is the fifth day of the month, so let’s suppose the daily shift key value is 5. The coding clerk will shift each letter
of the message 5 letters to the right in the following way:
‘A’ is enciphered to ‘F’
‘T’ is enciphered to ‘Y’
‘T’ is enciphered to ‘Y’
‘A’ is enciphered to ‘F’
‘C’ is enciphered to ‘H’
‘K’ is enciphered to ‘P
‘A’ is enciphered to ‘F’
‘T’ is enciphered to ‘Y’
‘D’ is enciphered to ‘I’
‘A’ is enciphered to ‘F’
‘W’ is enciphered to ‘B’ ( we wrap around back to the start of the alphabet if we go past ‘Z’)
‘N’ is enciphered to ‘S’
So, if we put the plaintext over the ciphertext, we get this:
Plaintext: ATTACK AT DAWN
Ciphertext: FYYFHP F Y I F B S
Now, if a courier was captured by the enemy, Caesar’s encoded orders would be unreadable. The courier did not
know the daily key value or how the encoding scheme worked, so even if the enemy tortured him, he could not
reveal anything.
It was not until about 800 years later that an Arab mathematician named Al-Khindi developed the concept of
frequency analysis and was able to apply it to deciphering such encoded messages. In a nutshell, any language
based on alphabetic characters uses some letters much more frequently than others. For example, in English the
most commonly used letter is the ‘E’, followed by ‘T’, ‘A’,’O’, ‘I’, ‘N’, ‘S’, and ‘R’ (www.letterfrequency.org). The
least frequently used letter is ‘Z’. By counting up the frequency of letters appearing in some ciphertext, code
breakers could make some good guesses as to which letter was an ‘E’, which was a ‘T’, etc. Then they would start
1
INFO1214 F2022substituting in their guesses to see if recognizable words started to appear. Once they got a few words, it didn’t
take long to decode the message.
There is an excellent series of short videos from the Khan Academy that detail how the Caesar shift cipher works.
Their URL’s are below. Take a few minutes and go view them now before reading any further.
https://www.khanacademy.org/computing/computer-science/cryptography/crypt/v/caesar-cipher
Requirements: The Caesar Shift Application
For the first part, you will code an app called Your_Initials_Regular_Caesar that will allow the user to either
encipher some plaintext into a coded message, or read an enciphered message and decode it into plaintext. The
use case for this program is as follows:
•Program gives a short explanation that it will either encipher or decipher a message entered by the user.
•Program asks user if they want to encipher or decipher a message by entering a 1 to encipher or a 2 to
decipher. Validate this input, either in the main(), or write a method to do this.
•If enciphering:
•
oprogram prompts user to enter the plaintext message and press enter when finished. This
message is stored as a String of UPPER CASE CHARACTERS. Use a method here to read in the
characters, convert them to UPPER CASE, and return the string.
oProgram then calls a method that asks user to enter a keyword of only letters, without spaces or
punctuation. This keyword input needs to be validated in the method. If user does enter any
spaces, punctuation marks, or numerals, the program should reject the keyword and ask the user
to re-enter it, and keep doing this until a valid keyword is entered. The method returns the valid
keyword as a string.
oProgram then calls another method to generate a shift value and passes it the validated keyword.
This shift value is calculated by converting each character in the keyword to its UPPER CASE
version, and then adding up the ASCII values of all the characters. Then, do a modulus division of
this total by 26 to get a shift value ranging from 0 to 25. If a zero value is returned because the
total was actually a multiple of 26, then add 1 the shift value so that we actually do encipher the
plaintext (a shift of zero means the plaintext and the ciphertext would be identical, which would
not be good message security!). This method returns the shift value as an int.
oProgram then calls another method to encipher the plaintext. This methods takes as arguments
the plaintext string and the shift value generated by the keyword validation method. This shift
value is then applied to each character in the plaintext to produce another String that will be the
ciphertext, or encoded message. The method returns this ciphertext string.
oThe plaintext and ciphertext messages are then displayed on separate lines in the console
window so that the user can see how the message has been encoded.
oProgram then asks user to enter a file name with a ‘.txt’ extension so that the ciphertext can be
written to a file. This method takes two string arguments, the file name and the ciphertext string,
and uses a PrintWriter object to write it to a file in your Eclipse project directory.
If deciphering:
oProgram asks if user will be typing in the ciphertext or entering it from a file.
oIf typing it in:

User enters the ciphertext and this is stored by the program as UPPER CASE characters.
Use the same method that you used to read in a plaintext message to be encoded.
2
INFO1214 F2022o
Program asks user to enter the keyword. Use your keyword validation method again to
validate it.
Use your method to take the keyword and calculate the shift to apply to the ciphertext.
Write another method that will properly apply the shift value to the ciphertext and
decipher each letter to its plaintext value. The plaintext characters are used to build a
new String that will be the plaintext message. The method returns this plaintext string
Display the original ciphertext and the deciphered plaintext in the console window.
If reading from a file:
Program asks user for the name of the file, such as “message.txt”.
Write a method that will take the file name as the argument and build a Scanner object
that will read the contents of the file. This method will return the contents of the file as
a string of which represents the ciphertext to be decoded.
Program asks user to enter the keyword. Validate this using your method..
Program then calls method to calculate the shift value and passes it the validated
keyword.
Use the decipher method to apply the shift value to the ciphertext and decipher each
letter to its plaintext value. Build a new String using the deciphered characters and
return this string
Display the original ciphertext and the deciphered plaintext in the console window.
Use Methods and Minimize Code in the main() Method
We want you to try to minimize the amount of code in your main() method, and do most of the work in various
methods mentioned above. To review, the required methods that you will need to write include:
•If the user is using keyboard entry, a method to read in the user’s plaintext or ciphertext message from
the keyboard that will convert it to UPPER CASE characters and then return this as a String that will be
passed as an argument to either an encipher() method or a decipher() method.
•A method to ask the user to enter a keyword. The method must validate the keyword. If the user passes
in an invalid keyword, the method tells them it is invalid and asks them to enter another keyword. Once
a valid keyword is obtained, the method returns it as a String.
•A method to generate the shift value to be applied to either encipher the plaintext or decipher the
ciphertext. This method will accept the valid keyword and calculate its value using the algorithm
described on page 2. It will return this shift value as an int.
•A method to encipher plaintext into ciphertext. This method will take as arguments the plaintext string
and the shift value. It will then apply the shift value to each character in the plaintext string and build a
new string that is the ciphertext string. NOTE: spaces and punctuation marks will not have the shift value
applied to them. Only apply the shift value to the letters. Leave the spaces and punctuation marks as
they are. The method returns the ciphertext string.
•A method to write the ciphertext to a file in your local Eclipse project directory. This method will take
two arguments of type String. The first argument will be the name of the file with the extension for a
text file (‘.txt’ extension), such as “secrectMessage.txt”. The second argument will be the ciphertext
string. The method will instantiate and use a PrintWriter object to write the ciphertext to a file.
•For deciphering, if the user specifies that the ciphertext will be read in from a file, you will need to ask
the user to enter the file name and extension (i.e. “secretMessage.txt”) and save this as a string. Then
you need to write a method that will take this file name as an argument. The method will build a Scanner
3
INFO1214 F2022object using the file name so that you can read the file’s contents. The file’s contents must be read and
converted to a String of UPPER CASE letters. This string is then returned as the ciphertext, which will
later be passed to the decipher() method.
•A method to decipher ciphertext into plaintext. This method will take as arguments the ciphertext string
and the shift value. It will then apply the shift value to each letter character in the ciphertext and build a
new string that is the plaintext string. Again, do not apply the shift to any spaces or punctuation marks.
The method returns the plaintext string.
•NOTE: all methods should be in a separate helper class named Your_Initials_Project_Methods.java.
Make sure that you include this file when you submit your project.
Below are some sample outputs from a prototype:
Doing an ENCIPHER
Prototype for a Caesar Shift Encoding Application.
This program will help you encipher a message or decipher a coded message.
If enciphering, enter 1 and press ENTER:
If deciphering, enter 2 and press ENTER:
Enter 1 or 2: 1
Enter the plaintext message you wish to encipher: Attack at dawn!
Enter a keyword of only letters, with no digits, spaces, or punctuation marks: Wednesday
The plaintext and enciphered text are as follows:
ATTACK AT DAWN!
BUUBDL BU EBXO!
Enciphered text will now be written to a text file.
Enter a file name followed by the extension '.txt' (example: secret.txt): secret.txt
Ciphertext has been written to local directory as secret.txt
Doing a DECIPHER:
Prototype for a Caesar Shift Encoding Application.
This program will help you encipher a message or decipher a coded message.
If enciphering, enter 1 and press ENTER:
If deciphering, enter 2 and press ENTER:
Enter 1 or 2: 2
We will be deciphering a message.
If ciphertext will be entered via keyboard, enter 1.
If ciphertext will be entered from a file, enter 2.
Enter 1 or 2: 1
Enter the ciphertext message you wish to decipher: buubdl bu ebxo!
Enter a keyword of only letters, with no digits, spaces, or punctuation marks: Wednesday
The ciphertext and deciphered plaintext are as follows:
BUUBDL BU EBXO!
ATTACK AT DAWN!
4
INFO1214 F2022Using a file as the source of the ciphertext…
Prototype for a Caesar Shift Encoding Application.
This program will help you encipher a message or decipher a coded message.
If enciphering, enter 1 and press ENTER:
If deciphering, enter 2 and press ENTER:
Enter 1 or 2: 2
We will be deciphering a message.
If ciphertext will be entered via keyboard, enter 1.
If ciphertext will be entered from a file, enter 2.
Enter 1 or 2: 2
Enter the name of the file containing the ciphertext: secret.txt
Enter a keyword of only letters, with no digits, spaces, or punctuation marks: Wednesday
The ciphertext and deciphered plaintext are as follows:
BUUBDL BU EBXO!
ATTACK AT DAWN!
Hot and Spicy Version: (4 bonus marks available if you are up to the challenge)
The Super Caesar Cipher
In order to make it more difficult to decode, what we’ll call the Super Caesar cipher was developed. In this
enhanced version of the Caesar cipher, each letter in the plaintext is shifted by a different shift value based on
some keyword, which could be an actual word or just a random selection of letters.
To demonstrate, let’s pick a short keyword for a simple example and use the word BAND, which has four letters.
Now, let’s assign a numeric value to each letter in the keyword using a ZERO-BASED INDEXING scheme like we do
with arrays. So, the letter A would be 0 (zero), B would be 1, C would be 2, D would be 3, E would be 4, etc.
Here are the letters of the alphabet and their corresponding values using ZERO-BASED INDEXING:
A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
The value of each letter in our keyword BAND is assigned its corresponding numeric value so it would look like this:
B A N D
1 0 13 3
These corresponding letter values tell us how far to shift each letter of our plaintext.
Suppose our plaintext message was “MEET AT ZOO”.
Using our keyword BAND, the B has a value of 1, so the first letter of our plaintext will be shifted to the right by 1
character. Therefore, the M of MEET becomes N.
MEET
N
For the second letter of MEET, E, we’ll use the second letter of BAND, the A, which has a value of 0, so the E is
shifted 0 letters to the right and just stays as an E. (NOTE: a shift of zero is okay in this Super Caesar encoding
scheme for a single letter, as each letter gets shifted by a different amount).
MEET
NE
For the third letter of MEET, another E, we use the third letter of BAND, the N, which has a value of 13, so we shift
the E 13 letters to the right to get an R.
MEET
NER
5
INFO1214 F2022For the fourth letter of MEET, T, we use the fourth letter of BAND, the D, which has a value of 3, so the T is shifted
3 letters to become a W.
MEET
NERW
Now we have cycled through our keyword BAND once, so we go back to the start of it and use the B again to
encipher the A of AT. We shift the A 1 letter to the right, so it becomes a B
MEET AT
NERW B
For the second letter of AT, the T, we use the A of BAND and apply a shift of 0(zero) letters to the right and just
leave it as a T.
MEET AT
NERW BT
For the Z in ZOO, we use the third letter of BAND, N, and apply a shift of 13. Since we are at the right end of the
alphabet, we wrap around back to the start and count 13 characters to arrive at the M.
NOTE: Don’t be fooled by the index numbers and use the N! Remember, we started indexing at 0, so starting a 0
and counting 13 spaces gets you to the index 12, which the letter M.
MEET AT ZOO
NERW BT M
For the first O in ZOO, we use the fourth letter of BAND, D, and apply a shift of 3 characters to get an R.
MEET AT ZOO
NERW BT MR
We have now cycled through our keyword BAND twice, so we go back to its first letter B and use it again for the
second O in ZOO. We apply a shift of 1 letter to the O and get the P. So, our final ciphertext looks like this:
MEET AT ZOO
NERW BT MRP
If you are interested in attempting the spicy version for a few bonus marks, then code it as a separate program
called Your_Intials_Super_Caesar.java. Most of your methods will stay the same, but you will need to adjust your
encipher() and decipher() methods to utilize the Super Caesar shift.
If you do try the spicy version make sure you submit BOTH programs (regular and super versions), to the drop box
as one zipped file.
