import matplotlib.pyplot as plt

vy = []
y_values = []

f = open("ball_data.txt", "r")
for row in f:
    row = row.split(",")
    vy.append(row[0])
    y_values.append(row[1])

plt.bar(vy, y_values, color = 'g', label = "Ball Data")

plt.xlabel("Y-Velocity", fontsize = 12)
plt.ylabel("Y-Values", fontsize = 12)

plt.legend()
plt.show()
           
