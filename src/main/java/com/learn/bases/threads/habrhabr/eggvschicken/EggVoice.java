package com.learn.bases.threads.habrhabr.eggvschicken;

class EggVoice extends Thread
{
    @Override
    public void run()
    {
        for(int i = 0; i < 5; i++)
        {
            try{
                sleep(1000);		//Приостанавливает поток на 1 секунду
            }catch(InterruptedException e){}
            
            System.out.println("яйцо!");	
        }
        //Слово «яйцо» сказано 5 раз
    }
}