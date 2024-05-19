def get_user_input():
    """
    Prompts the user to enter a text and returns the input.

    Returns:
        str: The input text from the user.
    """
    user_input = input("Enter a text: ")
    return user_input

def count_words(text):
    """
    Counts the number of words in the provided text.

    Args:
        text (str): The text to count words in.

    Returns:
        int: The number of words in the text.
    """
    # Remove leading and trailing whitespace
    text = text.strip()
    # Split text by spaces
    words = text.split()
    # Count the number of words
    return len(words)

def main():
    """
    Main function to run the word count program.
    """
    text = get_user_input()
    if not text:
        print("No input provided.")
    else:
        word_count = count_words(text)
        print(f"The number of words in the text is: {word_count}")

if __name__ == "_main_":
    main()