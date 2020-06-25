package com.mindflexibility.folklorearcana.objects.blocks;

import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SpecialBlock extends Block{
	
	
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	
	private static final VoxelShape SHAPE_N = Stream.of(
			Block.makeCuboidShape(8, 10, 12, 12, 11, 13),
			Block.makeCuboidShape(12, 4, 12, 13, 11, 13),
			Block.makeCuboidShape(12, 3, 8, 13, 4, 13),
			Block.makeCuboidShape(3, 11, 5, 4, 12, 8),
			Block.makeCuboidShape(3, 4, 4, 4, 12, 5),
			Block.makeCuboidShape(3, 3, 4, 8, 4, 5),
			Block.makeCuboidShape(0, 8, 8, 8, 16, 16),
			Block.makeCuboidShape(8, 0, 0, 16, 8, 8)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	private static final VoxelShape SHAPE_E = Stream.of(
			Block.makeCuboidShape(3, 10, 8, 4, 11, 12),
			Block.makeCuboidShape(3, 4, 12, 4, 11, 13),
			Block.makeCuboidShape(3, 3, 12, 8, 4, 13),
			Block.makeCuboidShape(8, 11, 3, 11, 12, 4),
			Block.makeCuboidShape(11, 4, 3, 12, 12, 4),
			Block.makeCuboidShape(11, 3, 3, 12, 4, 8),
			Block.makeCuboidShape(0, 8, 0, 8, 16, 8),
			Block.makeCuboidShape(8, 0, 8, 16, 8, 16)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	private static final VoxelShape SHAPE_S = Stream.of(
			Block.makeCuboidShape(4, 10, 3, 8, 11, 4),
			Block.makeCuboidShape(3, 4, 3, 4, 11, 4),
			Block.makeCuboidShape(3, 3, 3, 4, 4, 8),
			Block.makeCuboidShape(12, 11, 8, 13, 12, 11),
			Block.makeCuboidShape(12, 4, 11, 13, 12, 12),
			Block.makeCuboidShape(8, 3, 11, 13, 4, 12),
			Block.makeCuboidShape(8, 8, 0, 16, 16, 8),
			Block.makeCuboidShape(0, 0, 8, 8, 8, 16)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	
	
	private static final VoxelShape SHAPE_W = Stream.of(
			Block.makeCuboidShape(12, 10, 4, 13, 11, 8),
			Block.makeCuboidShape(12, 4, 3, 13, 11, 4),
			Block.makeCuboidShape(8, 3, 3, 13, 4, 4),
			Block.makeCuboidShape(5, 11, 12, 8, 12, 13),
			Block.makeCuboidShape(4, 4, 12, 5, 12, 13),
			Block.makeCuboidShape(4, 3, 8, 5, 4, 13),
			Block.makeCuboidShape(8, 8, 8, 16, 16, 16),
			Block.makeCuboidShape(0, 0, 0, 8, 8, 8)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	
	
	
	
	
	
	public SpecialBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH)); 
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch(state.get(FACING)) {
		case NORTH:
			return SHAPE_N;
		case SOUTH:
			return SHAPE_S;
		case EAST:
			return SHAPE_E;
		case WEST:
			return SHAPE_W;
		default:
			return SHAPE_N;
		}
	}
	
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
		
	}
	
	
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		// TODO Auto-generated method stub
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}
	
	
	
	
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	
	
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		if(!worldIn.isRemote()) {
			ServerWorld serverWorld = (ServerWorld)worldIn;
			LightningBoltEntity entity = new LightningBoltEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
			serverWorld.addLightningBolt(entity);	
		}
		return ActionResultType.SUCCESS;
	}
	
	
	
	
	
	
	

}
