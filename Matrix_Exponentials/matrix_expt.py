import numpy as np
import math

def mabs(m):
  return np.linalg.norm(m)

def mpower(m, n):
  prod = np.identity(m.shape[0]) #0 for rows 1 cor columns
  for _ in range(n):
    prod = np.dot(prod, m)
  return prod

def mat_expt_spec (m):
  eps = 1e-6
  sum = np.zeros_like(m, dtype='float64')
  prev = None
  n = 0
  while prev is None or mabs(prev-sum) >= eps:
    prev = np.matrix.copy(sum)
    term = mpower(m, n) / math.factorial(n)
    np.add(sum, term, out=sum, casting="unsafe")
    n += 1
  return sum

def expt_step(state):
  m, sum, n, prod = state
  return m, np.add(sum, prod, casting="unsafe"), n+1, np.dot(prod, m/(n+1))
#------------------CANT CHANGE
def close_enough(state1, state2, eps=1e-6):
  m1, sum1, n1, prod1 = state1
  m2, sum2, n2, prod2 = state2
  return mabs(sum1-sum2) < eps
  
def iterate (state, step, states_are_close):
  new_state = state
  while True:
    prev_state = new_state
    new_state = step(new_state)
    if states_are_close(new_state, prev_state):
      break
  return new_state
#------------------CANT CHANGE
def close_call(s1,s2):
  return close_enough(s1,s2,1e-7)
  
def mat_expt_v2 (m):
  initial_state = (
    m,
    np.zeros_like(m, dtype='float64'),
    0,
    np.identity(m.shape[0], dtype='float64')
  )
  m1, sum, n, prod = iterate (initial_state, expt_step, close_call)
  return sum
