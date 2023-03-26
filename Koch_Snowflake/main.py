"""
This is the Template Repl for Python with Turtle.

Python with Turtle lets you make graphics easily in Python.

Check out the official docs here: https://docs.python.org/3/library/turtle.html
"""

import turtle

from koch import koch_side, koch_snowflake

t = turtle.Turtle()    

cmds = koch_snowflake(100)
for c in cmds:
  if c[0] == 'forward':
    t.forward(c[1])
  elif c[0] == 'left':
    t.left(c[1])
  elif c[0] == 'right':
    t.right(c[1])
  else:
    raise ValueError('Unrecognized turtle command:', c)