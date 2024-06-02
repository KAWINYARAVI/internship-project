import random
import string

def generate_password(length):
    """Generate a single password of given length."""
    if length < 4:
        raise ValueError("Password length should be at least 4")
    
    # Define character sets
    lower = string.ascii_lowercase
    upper = string.ascii_uppercase
    digits = string.digits
    special = string.punctuation
    
    # Ensure the password has at least one character from each set
    password = [
        random.choice(lower),
        random.choice(upper),
        random.choice(digits),
        random.choice(special)
    ]
    
    # Fill the rest of the password length with random choices from all sets combined
    all_characters = lower + upper + digits + special
    password += random.choices(all_characters, k=length-4)
    
    # Shuffle the list to ensure random order
    random.shuffle(password)
    
    return ''.join(password)

def generate_passwords(num_passwords, length):
    """Generate a list of passwords."""
    return [generate_password(length) for _ in range(num_passwords)]

def main():
    # User instructions
    print("Welcome to the Password Generator!")
    print("Please follow the instructions to generate secure passwords.")
    
    # Get user input for number of passwords and length
    try:
        num_passwords = int(input("Enter the number of passwords to generate: "))
        length = int(input("Enter the length of the passwords: "))
        
        # Generate passwords
        passwords = generate_passwords(num_passwords, length)
        
        # Display the passwords
        print("\nGenerated Passwords:")
        for i, password in enumerate(passwords, 1):
            print(f"{i}: {password}")
    
    except ValueError as e:
        print(f"Invalid input: {e}")

if __name__ == "__main__":
    main()
