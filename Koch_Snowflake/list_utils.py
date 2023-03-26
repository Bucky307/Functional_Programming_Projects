def simple_map(fn, l):
  m = []
  for i in l:
    m.append(fn(i))
  return m      # TODO

def flat_map(fn, l): #if it returns multiple lists flatten into one list
  fm = []
  for i in l:
    for j in fn(i):
      fm.append(j)
  return fm   # TODO
