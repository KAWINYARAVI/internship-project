class Quiz:
    def __init__(self, questions):
        self.questions = questions
        self.score = 0

    def display_question(self, question):
        print(question['question'])
        for idx, option in enumerate(question['options'], 1):
            print(f"{idx}. {option}")

    def get_user_answer(self):
        while True:
            try:
                choice = int(input("Enter your choice: "))
                if 1 <= choice <= len(self.questions[0]['options']):
                    return choice
                else:
                    print("Invalid choice. Please enter a number within the given range.")
            except ValueError:
                print("Invalid input. Please enter a number.")

    def check_answer(self, question, user_answer):
        correct_answer = question['answer']
        if user_answer == correct_answer:
            print("Correct!")
            self.score += 1
        else:
            print("Incorrect. The correct answer is:", question['options'][correct_answer - 1])

    def run_quiz(self):
        for question in self.questions:
            self.display_question(question)
            user_choice = self.get_user_answer()
            self.check_answer(question, user_choice)
        print("Quiz completed!")
        print("Your final score is:", self.score)


if __name__ == "__main__":
    questions = [
        {
            "question": "What does CPU stand for?",
            "options": ["Central Processing Unit", "Computer Processing Unit", "Central Program Unit"],
            "answer": 1
        },
        {
            "question": "Which component is known as the 'brain' of the computer?",
            "options": ["CPU", "RAM", "Hard Drive"],
            "answer": 1
        },
        {
            "question": "What does RAM stand for?",
            "options": ["Random Access Memory", "Read-Only Memory", "Real-time Access Memory"],
            "answer": 1
        },
        {
            "question": "Which of the following is a volatile memory?",
            "options": ["Hard Disk Drive (HDD)", "Solid State Drive (SSD)", "RAM"],
            "answer": 3
        },
        {
            "question": "What is the purpose of an operating system?",
            "options": ["To manage computer hardware and software resources", "To browse the internet", "To create documents"],
            "answer": 1
        }
    ]

    quiz = Quiz(questions)
    quiz.run_quiz()
