import json
from datetime import datetime

# File to store expenses
DATA_FILE = 'expenses.json'

# Categories of expenses
CATEGORIES = ['food', 'transportation', 'entertainment', 'utilities', 'others']

def get_expense_input():
    try:
        amount = float(input("Enter the amount spent: "))
        description = input("Enter a brief description: ")
        category = input(f"Enter the category ({', '.join(CATEGORIES)}): ")
        if category not in CATEGORIES:
            raise ValueError("Invalid category")
        date = input("Enter the date (YYYY-MM-DD): ")
        datetime.strptime(date, "%Y-%m-%d")  # Validate date format
        return {"amount": amount, "description": description, "category": category, "date": date}
    except ValueError as e:
        print(f"Invalid input: {e}")
        return None

def save_expenses(expenses, filename=DATA_FILE):
    with open(filename, 'w') as file:
        json.dump(expenses, file)

def load_expenses(filename=DATA_FILE):
    try:
        with open(filename, 'r') as file:
            return json.load(file)
    except FileNotFoundError:
        return []

def add_expense(expenses):
    expense = get_expense_input()
    if expense:
        expenses.append(expense)
        save_expenses(expenses)
        print("Expense added successfully!")

def monthly_summary(expenses, month):
    summary = [expense for expense in expenses if expense['date'].startswith(month)]
    total = sum(expense['amount'] for expense in summary)
    return total, summary

def category_summary(expenses, category):
    summary = [expense for expense in expenses if expense['category'] == category]
    total = sum(expense['amount'] for expense in summary)
    return total, summary

def show_monthly_summary(expenses):
    month = input("Enter the month (YYYY-MM): ")
    total, summary = monthly_summary(expenses, month)
    print(f"Total expenses for {month}: {total}")
    for expense in summary:
        print(expense)

def show_category_summary(expenses):
    category = input("Enter the category: ")
    if category not in CATEGORIES:
        print("Invalid category")
        return
    total, summary = category_summary(expenses, category)
    print(f"Total expenses for category '{category}': {total}")
    for expense in summary:
        print(expense)

def main():
    expenses = load_expenses()
    while True:
        print("\nExpense Tracker Menu")
        print("1. Add an expense")
        print("2. View monthly summary")
        print("3. View category summary")
        print("4. Exit")
        choice = input("Enter your choice: ")

        if choice == '1':
            add_expense(expenses)
        elif choice == '2':
            show_monthly_summary(expenses)
        elif choice == '3':
            show_category_summary(expenses)
        elif choice == '4':
            break
        else:
            print("Invalid choice. Please try again.")

if __name__ == "__main__":
    main()
