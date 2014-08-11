package com.learn.patterns.freemanAndFreeman.headfirst.iterator.custom;

import java.util.List;

/**
 * Created by bid on 8/11/14.
 */
public class UserStoryIterator implements MyIterator
{

   List items;
   int position;

   public UserStoryIterator(final List items)
   {
      this.items = items;
   }

   public int getPosition()
   {
      return position;
   }

   public void setPosition(final int position)
   {
      this.position = position;
   }

   public List getItems()
   {
      return items;
   }

   public void setItems(final List items)
   {
      this.items = items;
   }

   @Override
   public Object next()
   {
      if(items.size() != 0){
         position += 1;
         return items.get(position);
      }
      return null;
   }

   @Override
   public boolean hasNext()
   {
      return false;
   }
}
