def count_words(text):
    """
    Function to count the number of words in a given text.
    A word is defined as any sequence of characters separated by whitespace.
    """
    # Split the text into words based on whitespace
    words = text.split()
    # Return the number of words
    return len(words)

def main():
    """
    Main function to run the word counter program.
    Handles user input, calls the word counting function,
    and displays the result.
    """
    # Prompt the user to enter a sentence or paragraph
    user_input = input("Please enter a sentence or paragraph: ")

    # Error handling for empty input
    if not user_input.strip():
        print("Error: No input provided. Please enter some text.")
        return

    # Count the number of words in the input
    word_count = count_words(user_input)

    # Display the word count
    print(f"The number of words in the given text is: {word_count}")

# Run the main function if this script is executed
if __name__ == "__main__":
    main()
