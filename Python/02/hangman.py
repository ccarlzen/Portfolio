# Problem Set 2, hangman.py
# Name: Casey Carlzen
# Collaborators: None
# Time spent:8-16hrs

# Hangman Game
# -----------------------------------

import random
import string

WORDLIST_FILENAME = "words.txt"


def load_words():
    """
    Returns a list of valid words. Words are strings of lowercase letters.
    
    Depending on the size of the word list, this function may
    take a while to finish.
    """
    print("Loading word list from file...")
    # inFile: file
    inFile = open(WORDLIST_FILENAME, 'r')
    # line: string
    line = inFile.readline()
    # wordlist: list of strings
    wordlist = line.split()
    print("  ", len(wordlist), "words loaded.")
    return wordlist



def choose_word(wordlist):
    """
    wordlist (list): list of words (strings)
    
    Returns a word from wordlist at random
    """
    return random.choice(wordlist)

# Load the list of words into the variable wordlist
# so that it can be accessed from anywhere in the program
wordlist = load_words()


def is_word_guessed(secret_word, letters_guessed):
    '''
    secret_word: string, the word the user is guessing; assumes all letters are
      lowercase
    letters_guessed: list (of letters), which letters have been guessed so far;
      assumes that all letters are lowercase
    returns: boolean, True if all the letters of secret_word are in letters_guessed;
      False otherwise
    '''
    word_guessed = True
    for letter in secret_word:
        if letter not in letters_guessed:
            word_guessed = False
    return word_guessed
    



def get_guessed_word(secret_word, letters_guessed):
    '''
    secret_word: string, the word the user is guessing
    letters_guessed: list (of letters), which letters have been guessed so far
    returns: string, comprised of letters, underscores (_), and spaces that represents
      which letters in secret_word have been guessed so far.
    '''
    ls = []
    for letter in secret_word:
        if letter in letters_guessed:
            ls.append(letter)
        else:
            ls.append("_ ")
    return "".join(ls)


def get_available_letters(letters_guessed):
    '''
    letters_guessed: list (of letters), which letters have been guessed so far
    returns: string (of letters), comprised of letters that represents which letters have not
      yet been guessed.
    '''
    ls = list(string.ascii_lowercase)
    for letter in letters_guessed:
        if letter in ls:
            ls.remove(letter)
    return "".join(ls)
    
def is_vowel(letter):
    vowels = list('aeiou')
    return letter in vowels
    

def hangman(secret_word):
    '''
    secret_word: string, the secret word to guess.
    
    Starts up an interactive game of Hangman.
    
    * At the start of the game, let the user know how many 
      letters the secret_word contains and how many guesses s/he starts with.
      
    * The user should start with 6 guesses

    * Before each round, you should display to the user how many guesses
      s/he has left and the letters that the user has not yet guessed.
    
    * Ask the user to supply one guess per round. Remember to make
      sure that the user puts in a letter!
    
    * The user should receive feedback immediately after each guess 
      about whether their guess appears in the computer's word.

    * After each guess, you should display to the user the 
      partially guessed word so far.
    
    Follows the other limitations detailed in the problem write-up.
    '''
    guesses = 6
    warnings = 3
    guessed_letters = []
    print("Welcome to the game Hangman!")
    print("I am thinking of a word that is {} letters long.".format(len(secret_word)))
    print("You have {} warnings left".format(warnings))
    print(get_guessed_word(secret_word, guessed_letters))
    
    while guesses > 0:
        # print statements at the beginning of every round
        print("You have {} guesses left.".format(guesses))
        print("Available letters: {}".format(get_available_letters(guessed_letters)))
        
        # collect and check a guess
        # is alphabetical?
        # has it been guessed already?
        # is it in the word?
        # --Warns user if they received a warning
        guess = (input("Please guess a letter: ")).lower()
        if not str.isalpha(guess):
            if warnings > 0:
                warnings -= 1
            else:
                guesses -= 1
            print("Oops! That is not a valid letter. You have {} warnings left: {}".format(warnings, get_guessed_word(secret_word, guessed_letters)))
        elif guess not in get_available_letters(guessed_letters):
            if warnings > 0:
                warnings -= 1
            else:
                guesses -= 1
            print("You've already guessed this letter. You have {} warnings left: {}".format(warnings, get_guessed_word(secret_word, guessed_letters)))
        else:
            guessed_letters.append(guess)
            if guess in secret_word:
                print("Good guess: {}".format(get_guessed_word(secret_word, guessed_letters)))
            else:
                print("Oops! That letter is not in my word: {}".format(get_guessed_word(secret_word, guessed_letters)))
                if is_vowel(guess):
                    guesses -= 2
                else:
                    guesses -= 1
                
        # if word is guessed early, exit game loop
        if is_word_guessed(secret_word, guessed_letters):
            break
        
        #prints after every round to make rounds visible
        print("----------")
    
    
    #print win/loss
    if is_word_guessed(secret_word, guessed_letters):
        print("Congratulations, you won!".format(secret_word))
        score = guesses * len(set(secret_word))
        print("Your total score for this game is: {}".format(score))
    else:
        print("Sorry you ran out of guesses. The word was : {}".format(secret_word))
        
        
def match_with_gaps(my_word, other_word):
    '''
    my_word: string with _ characters, current guess of secret word
    other_word: string, regular English word
    returns: boolean, True if all the actual letters of my_word match the 
        corresponding letters of other_word, or the letter is the special symbol
        _ , and my_word and other_word are of the same length;
        False otherwise: 
    '''
    my_word = my_word.replace(" ", "")
    used_letters = []
    
    if len(my_word) != len(other_word):
        return False
    
    for index in range(len(other_word)):
        if my_word[index].isalpha():
            if my_word[index] != other_word[index]:
                return False
            elif my_word[index] in used_letters:
                return False
        elif my_word[index] == '_':
            used_letters.append(other_word[index])
            if other_word[index] in my_word:
                return False
    
    return True


def show_possible_matches(my_word):
    '''
    my_word: string with _ characters, current guess of secret word
    returns: nothing, but should print out every word in wordlist that matches my_word
             Keep in mind that in hangman when a letter is guessed, all the positions
             at which that letter occurs in the secret word are revealed.
             Therefore, the hidden letter(_ ) cannot be one of the letters in the word
             that has already been revealed.

    '''
    matches = []
    for word in wordlist:
        if match_with_gaps(my_word, word):
            matches.append(word)
            
    if len(matches) > 0:
        print(" ".join(matches))
    else:
        print("No matches found")


def hangman_with_hints(secret_word):
    '''
    secret_word: string, the secret word to guess.
    
    Starts up an interactive game of Hangman.
    
    * At the start of the game, let the user know how many 
      letters the secret_word contains and how many guesses s/he starts with.
      
    * The user should start with 6 guesses
    
    * Before each round, you should display to the user how many guesses
      s/he has left and the letters that the user has not yet guessed.
    
    * Ask the user to supply one guess per round. Make sure to check that the user guesses a letter
      
    * The user should receive feedback immediately after each guess 
      about whether their guess appears in the computer's word.

    * After each guess, you should display to the user the 
      partially guessed word so far.
      
    * If the guess is the symbol *, print out all words in wordlist that
      matches the current guessed word. 
    
    Follows the other limitations detailed in the problem write-up.
    '''
    guesses = 6
    warnings = 3
    guessed_letters = []
    print("Welcome to the game Hangman!")
    print("I am thinking of a word that is {} letters long.".format(len(secret_word)))
    print("You have {} warnings left".format(warnings))
    print(get_guessed_word(secret_word, guessed_letters))
    
    while guesses > 0:
        # print statements at the beginning of every round
        print("You have {} guesses left.".format(guesses))
        print("Available letters: {}".format(get_available_letters(guessed_letters)))
        
        # collect and check a guess
        # is alphabetical?
        # has it been guessed already?
        # is it in the word?
        # --Warns user if they received a warning
        guess = (input("Please guess a letter: ")).lower()
        if guess == "*":
            print("Possible word matches are:")
            show_possible_matches(str(get_guessed_word(secret_word, guessed_letters)))
        elif not str.isalpha(guess):
            if warnings > 0:
                warnings -= 1
            else:
                guesses -= 1
            print("Oops! That is not a valid letter. You have {} warnings left: {}".format(warnings, get_guessed_word(secret_word, guessed_letters)))
        elif guess not in get_available_letters(guessed_letters):
            if warnings > 0:
                warnings -= 1
            else:
                guesses -= 1
            print("You've already guessed this letter. You have {} warnings left: {}".format(warnings, get_guessed_word(secret_word, guessed_letters)))
        else:
            guessed_letters.append(guess)
            if guess in secret_word:
                print("Good guess: {}".format(get_guessed_word(secret_word, guessed_letters)))
            else:
                print("Oops! That letter is not in my word: {}".format(get_guessed_word(secret_word, guessed_letters)))
                if is_vowel(guess):
                    guesses -= 2
                else:
                    guesses -= 1
                
        # if word is guessed early, exit game loop
        if is_word_guessed(secret_word, guessed_letters):
            break
        
        #prints after every round to make rounds visible
        print("----------")    
    
    #print win/loss
    if is_word_guessed(secret_word, guessed_letters):
        print("Congratulations, you won!".format(secret_word))
        score = guesses * len(set(secret_word))
        print("Your total score for this game is: {}".format(score))
    else:
        print("Sorry you ran out of guesses. The word was : {}".format(secret_word))
        

if __name__ == "__main__":
    pass

    # To test part 2, comment out the pass line above and
    # uncomment the following two lines.
    
    #secret_word = choose_word(wordlist)
    #hangman(secret_word)
    
    #word = '_ a_ _ _ '
    #show_possible_matches(word)
    
###############
    
    # To test part 3 re-comment out the above lines and 
    # uncomment the following two lines. 
    
    #secret_word = choose_word(wordlist)
    #hangman_with_hints(secret_word)
