import numpy as np, scipy.stats as st
import matplotlib.pyplot as plt

file_path = 'src/assignment_2/5k_run_1'

with open(file_path, 'r') as file:
    file.seek(0)
    lines = file.readlines()

values = np.array([int(k) for k in lines])

ci = st.t.interval(0.95, len(values)-1, loc=np.mean(values), scale=st.sem(values))
print(ci[1] - ci[0])