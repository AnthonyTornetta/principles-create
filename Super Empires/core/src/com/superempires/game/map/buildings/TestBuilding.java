package com.superempires.game.map.buildings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.superempires.game.objects.properties.Transform;
import com.superempires.game.registry.GameRegistry;

public class TestBuilding extends Building
{
	private Texture texture = GameRegistry.getTexture("building");
	
	public TestBuilding(Transform t)
	{
		super(t);
	}

	@Override
	public void drawShapes(ShapeRenderer batch)
	{
		
	}

	@Override
	public void drawLines(ShapeRenderer batch)
	{
		
	}

	@Override
	public void drawPolygons(PolygonSpriteBatch batch)
	{
		
	}

	@Override
	public void drawSprites(SpriteBatch batch)
	{
		batch.draw(texture, getTransform().getX(), getTransform().getY(), getTransform().getWidth(), getTransform().getHeight());
	}
}
