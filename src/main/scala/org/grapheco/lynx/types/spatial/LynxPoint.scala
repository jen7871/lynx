package org.grapheco.lynx.types.spatial

import org.grapheco.lynx.types.{LTPoint, LynxType, LynxValue, TypeMismatchException}
import org.grapheco.lynx.procedure.ProcedureException
import org.grapheco.lynx.runner.ParsingException
import org.grapheco.lynx.types.composite.LynxMap
import org.grapheco.lynx.types.property.{LynxFloat, LynxInteger, LynxNull, LynxString}
import org.grapheco.lynx.types.spatial.SpatialType.SpatialType
import org.grapheco.lynx.types.structural.LynxPropertyKey
import org.grapheco.lynx.types.traits.HasProperty

trait LynxPoint extends LynxValue with HasProperty{
  val x: LynxFloat

  val y: LynxFloat

  val crs: LynxString

  val srid: LynxInteger

  override def lynxType: LynxType = LTPoint

  override def value: Any = this

  override def keys: Seq[LynxPropertyKey] = Seq("x", "y", "crs", "srid").map(LynxPropertyKey)

  override def property(propertyKey: LynxPropertyKey): Option[LynxValue] = Some(propertyKey.value match {
    case "x" => this.x
    case "y" => this.y
    case "crs" => this.crs
    case "srid" => this.srid
    case _ => LynxNull
  })

  override def sameTypeCompareTo(o: LynxValue): Int = ???
}
object SpatialType extends Enumeration {
  type SpatialType = Value
  val WGS_84_2D, WGS_84_3D, Cartesian2D, Cartesian3D = Value
}
object LynxPoint{

  val crsNames: Map[String, SpatialType] = Map(
    "wgs-84" -> SpatialType.WGS_84_2D,
    "wgs-84-3d" -> SpatialType.WGS_84_3D,
    "cartesian" -> SpatialType.Cartesian2D,
    "cartesian-3d" -> SpatialType.Cartesian3D)

  val srids: Map[Int, SpatialType] = Map(
    4326 -> SpatialType.WGS_84_2D,
    4979 -> SpatialType.WGS_84_3D,
    7203 ->SpatialType.Cartesian2D,
    9157 ->SpatialType.Cartesian3D)


}

