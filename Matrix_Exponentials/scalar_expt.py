import math


# exp(x) = 1 + x + x^2/2! + x^3/3! + ...
def num_expt_spec (x):
  eps = 1e-6
  sum = 0.0
  prev = None
  n = 0
  while prev is None or abs(prev-sum) >= eps:
    prev = sum
    sum += x**n / math.factorial(n)
    n += 1
  return sum










def num_expt (x):
  eps = 1e-6
  sum = 0.0
  prod = 1.0
  prev = None
  n = 0
  while prev is None or abs(prev-sum) >= eps:
    prev = sum
    sum += prod
    n += 1
    prod *= x/n
  return sum







  

def expt_step(state):
  x, sum, n, prod = state
  return x, sum+prod, n+1, prod*x/(n+1)

def expt_value(state):
  x, sum, n, prod = state
  return sum

def iterate (state, step, value, eps=1e-6):
  new_state = state
  while True:
    prev_state = new_state
    new_state = step(new_state)
    if abs(value(prev_state)-value(new_state)) < eps:
      break
  return value(new_state)

def num_expt_v2 (x):
  return iterate ((x, 0.0, 0, 1.0), expt_step, expt_value)

print(num_expt(1))