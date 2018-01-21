##   Casey Carlzen
#   01/18/2018
#   Write a program to calculate how many months it will take you to save up enough money 
#   for a down payment.

# Collect user input
annual_salary = float(input("Enter your annual salary: "))
portion_saved = float(input("Enter the percent of your salary to save, as a decimal: "))
total_cost = float(input("Enter the cost of your dream home: "))
semi_annual_raise = float(input("Enter the semi-annual raise, as a decimal: "))

# Convert yearly to monthly
monthly_salary = annual_salary/12

# Initialize some rates and values
r = 0.04
portion_down_payment = total_cost * 0.25
current_savings = 0
months = 0

# Calculate num of months
while current_savings <= portion_down_payment:
    months += 1
    current_savings +=  current_savings*r/12 + monthly_salary*portion_saved
    if months%6 == 0:
        annual_salary += annual_salary*semi_annual_raise
        monthly_salary = annual_salary/12

# Display output
print("Number of months: {}".format(months))
print(current_savings)
