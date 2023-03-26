from list_utils import flat_map

def transform(cmd, minside=10):
  if cmd[0] != 'forward' or cmd[1] < minside:
    return [cmd]
  return [['forward', cmd[1]/3], ['right', 60], ['forward', cmd[1]/3], ['left', 120], ['forward', cmd[1]/3], ['right', 60], ['forward', cmd[1]/3]]       # TODO

def iterate(fn, pattern):
  x = []
  y = pattern
  while x != y:
    x = y
    y = flat_map(fn, x)
  
  return y      # TODO
  
def koch_side(sidelength):
  return iterate(transform, [['forward', sidelength]])
    
def koch_snowflake(sidelength):
  side = koch_side(sidelength)
  return 3 * (side + [['left', 120]])