package com.learn.patterns.freemanAndFreeman.headfirst.iterator.custom;

/**
 * Created by bid on 8/11/14.
 */
public class BugTaskIterator implements MyIterator
{
   Task[] items;
   int position;

   public BugTaskIterator(final Task[] items)
   {
      this.items = items;
   }

   public Task[] getItems()
   {
      return items;
   }

   public void setItems(final Task[] items)
   {
      this.items = items;
   }

   @Override
   public Object next()
   {
      if(items.length != 0){
         position += 1;
         return items[position];
      }
      return null;
   }

   @Override
   public boolean hasNext()
   {
      return position < items.length;
   }
}
