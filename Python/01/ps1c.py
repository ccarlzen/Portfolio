##   Casey Carlzen
#   01/18/2018
#   Write a program to calculate how many months it will take you to save up enough money 
#   for a down payment.

semi_annual_raise = 0.07
r = 0.04
total_cost = 1000000
portion_down_payment = total_cost * 0.25
months = 36

starting_salary = float(input("Enter the starting salary: "))
monthly_salary = starting_salary/12

high_guess = 10000
low_guess = 0
guess = (high_guess+low_guess)//2
guess_rate = guess/10000



current_savings = 0
counter = 0
is_rate = True

while abs(current_savings-portion_down_payment) >= 100:
    counter += 1
    new_starting_salary = starting_salary
    new_monthly_salary = new_starting_salary/12
    current_savings = 0
    
    for month in range(1, months+1):
        current_savings += current_savings*r/12 + new_monthly_salary*guess_rate
        if month%6 == 0:
            new_starting_salary += new_starting_salary*semi_annual_raise
            new_monthly_salary = new_starting_salary/12
    
    if current_savings > portion_down_payment:
        high_guess = guess
        guess = (high_guess+low_guess)//2
        guess_rate = guess/10000
    elif current_savings < portion_down_payment:
        low_guess = guess
        guess = (high_guess+low_guess)//2
        guess_rate = guess/10000
    
    if guess_rate == .9999:
        is_rate = False
        break

if is_rate:        
    print("Best savings rate: {}".format(guess_rate))
    print("Steps in bisection search: {}".format(counter))
else:
    print("It is not possible to pay the down payment in three years.")