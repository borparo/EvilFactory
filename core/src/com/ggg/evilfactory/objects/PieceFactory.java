package com.ggg.evilfactory.objects;

import com.ggg.evilfactory.game.AbstractPiece;

/**
 * Created by borja on 14-8-28.
 */
public class PieceFactory
{
    public PieceFactory()
    {

    }

   public static AbstractPiece getPieceType(String pieceType)
   {
       AbstractPiece newPiece = null;

       if(pieceType.equalsIgnoreCase("Weapon"))
       {
           newPiece =  new WeaponPiece();
       }

       if(pieceType.equalsIgnoreCase("Gadget"))
       {
           newPiece = new GadgetPiece();
       }

       if(pieceType.equalsIgnoreCase("Suit"))
       {
           newPiece = new SuitPiece();
       }

       if(pieceType.equalsIgnoreCase("Bomb"))
       {
           newPiece = new Bomb();
       }

       if(pieceType.equalsIgnoreCase("Coin"))
       {
           newPiece = new Coin();
       }


       return newPiece;

   }
}
