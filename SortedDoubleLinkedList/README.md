# Sorted Double Linked List

## Public methods

void	add(Item item)
Adds the specified item in the list.

int	indexOf(Item item)
Returns the index of the first specified Item inside the list.

int	indexOf(Item item, java.util.Comparator<Item> comp)
Returns the index of the first specified Item inside the list using Custom Compartor.

boolean	isEmpty()
Returns true if list is empty.

java.util.Iterator<Item>	iterator() 
boolean	remove(int index)
Removes the Item in the index possition.

boolean	remove(Item item)
Removes a specified Item from the list.

int	size()
Returns the number of elements inside the list.

void	traverse(Actionator<Item> action) 
Traverse the LinkedList and perform a action in each element.
