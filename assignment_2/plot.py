import numpy as py
import matplotlib.pyplot as plt

# plt.plot([1, 2, 3, 4])
# plt.ylabel('some numbers')
# # plt.show()

# Specify the path to your file
file_path = 'src/assignment_2/5k_run_1'

# Use 'r' for read mode
with open(file_path, 'r') as file:
    # Option 1: Read the entire file content into a single string
    # content = file.read()
    # print("--- Entire File Content ---")
    # print(content)
    
    # # Option 2: Read file line-by-line into a list
    file.seek(0) # Reset file pointer to the beginning for re-reading
    lines = file.readlines()
    print("--- First 5 Lines ---")
    print(len(lines))