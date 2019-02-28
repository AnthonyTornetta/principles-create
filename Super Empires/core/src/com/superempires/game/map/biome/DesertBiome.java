package com.superempires.game.map.biome;

import com.badlogic.gdx.graphics.Color;
import com.superempires.game.map.tiling.DesertTile;
import com.superempires.game.map.tiling.Tile;

public class DesertBiome extends Biome
{
	@Override
	public void generateTile(Tile[][] tiles, int x, int y, double temperature)
	{
		tiles[y][x] = new DesertTile(x, y, temperature, this);
	}

	@Override
	public Color getShadingColor()
	{
		return Color.WHITE;
	}
	
	@Override
	public int getRarity()
	{
		return 100;
	}

	@Override
	public float getPreferredAverageTemperature()
	{
		return 100;
	}

	@Override
	public boolean isAcceptableTemperature(double temperature)
	{
		return 90 <= temperature && temperature <= 130;
	}
}