package com.company.entitys.statics;

import com.company.Handler;
import com.company.entitys.Entity;

public abstract class StaticEntity extends Entity {//son entidades que no se mueven


    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

}
