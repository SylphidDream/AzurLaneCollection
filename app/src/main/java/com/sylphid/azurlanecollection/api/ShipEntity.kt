package com.sylphid.azurlanecollection.api

data class ShipEntity(
    val id: String? = "",
    val wikiUrl: String? = null,
    val _gid: Int? = null,
    val _sid: List<Int>? = null,
    val _code: Int? = null,
    val names: Name? = null,
    val exists: Exists? = null,
    val hexagon: List<Int>? = null,
    val shipClass: String? = null,
    val nationality: String? = null,
    val hullType: String? = null,
    val thumbnail: String?= null,
    val rarity: String?= null,
    val stars: Int?= null,
    val stats: Stats?= null,
    val slots: List<Slot>?= null,
    val enhanceValue: EnhanceValue?= null,
    val scrapValue: ScrapValue?= null,
    val skills: List<Skill>?= null,
    val limitBreaks: List<List<String>>?= null,
    val fleetTech: FleetTech?= null,
    val retrofit: Boolean?= null,
    val retrofitId: String?= null,
    val retrofitProjects: RetrofitProjects?= null,
    val retrofitHullType: String?= null,
    val construction: Construction?= null,
    val obtainedFrom: ObtainMethods?= null,
    val misc: Miscellaneous?= null,
    val skins: List<Skin>?= null,
    val gallery: List<ShipGallery>?= null
)

data class Name(
    val en: String?= null,
    val code: String?= null,
    val cn: String?= null,
    val jp: String?= null,
    val kr: String?= null,
    val tw: String?= null
)

data class Exists(
    val en: Boolean?= null,
    val cn: Boolean?= null,
    val jp: Boolean?= null,
    val kr: Boolean?= null
)

data class Stats(
    val baseStats: StatDetails?= null,
    val level100: StatDetails?= null,
    val level100Retrofit: StatDetails?= null,
    val level120: StatDetails?= null,
    val level120Retrofit: StatDetails?= null,
    val level125: StatDetails?= null,
    val level125Retrofit: StatDetails?= null

)

data class StatDetails(
    val health: String?= null,
    val armor: String?= null,
    val reload: String?= null,
    val luck: String?= null,
    val firepower: String?= null,
    val torpedo: String?= null,
    val evasion: String?= null,
    val speed: String?= null,
    val antiair: String?= null,
    val aviation: String?= null,
    val oilConsumption: String?= null,
    val accuracy: String?= null,
    val antisubmarineWarfare: String?= null,
    val oxygen: String?= null,
    val ammunition: String?= null,
    val huntingRange: String?= null
)

data class Slot(
    val maxEfficiency: Int?= null,
    val minEfficiency: Int?= null,
    val type: String?= null,
    val max: Int?= null,
    val kaiEfficiency: Int?= null
)

data class EnhanceValue(
    val firepower: Int?= null,
    val torpedo: Int?= null,
    val aviation: Int?= null,
    val reload: Int?= null
)

data class ScrapValue(
    val coin: Int?= null,
    val oil: Int?= null,
    val medal: Int?= null
)

data class Skill(
    val icon: String?= null,
    val names: Name?= null,
    val description: String?= null,
    val color: String?= null
)
data class FleetTech(
    val statsBonus: StatsBonus?= null,
    val techPoints: TechPoints?= null
)

data class StatsBonus(
    val collection: StatsBonusCollection?= null,
    val maxLevel: MaxLevel?= null
)

//Likely change here
data class StatsBonusCollection(
    val applicable: List<String>?= null,
    val bonus: String?= null,
    val stat: String?= null
)

data class MaxLevel(
    val applicable: List<String>?= null,
    val bonus: String?= null,
    val stat: String?= null
)

data class TechPoints(
    val collection: Int?= null,
    val maxLimitBreak: Int?= null,
    val maxLevel: Int?= null,
    val total: Int?= null
)

data class RetrofitProjects(
    val A: RetrofitProject?= null,
    val B: RetrofitProject?= null,
    val C: RetrofitProject?= null,
    val D: RetrofitProject?= null,
    val E: RetrofitProject?= null,
    val F: RetrofitProject?= null,
    val G: RetrofitProject?= null,
    val H: RetrofitProject?= null,
    val I: RetrofitProject?= null,
    val J: RetrofitProject?= null,
    val K: RetrofitProject?= null,
    val L: RetrofitProject?= null
)

data class RetrofitProject(
    val id: String?= null,
    val attributes: List<String>?= null,
    val materials: List<String>?= null,
    val coins: Int?= null,
    val level: Int?= null,
    val levelBreakLevel: Int?= null,
    val levelBreakStars: String?= null,
    val recurrence: Int?= null,
    val require: List<String>?= null
)

data class Construction(
    val constructionTime: String?= null,
    val availableIn: ConstructionMethods?= null
)

data class ConstructionMethods(
    val light: Boolean?= null,
    val heavy: Boolean?= null,
    val aviation: Boolean?= null,
    val limited: Boolean?= null,
    val exchange: Boolean?= null
)

data class ObtainMethods(
    val fromMaps: List<String>?= null
)

data class Miscellaneous(
    val artist: Artist?= null,
    val voice: ShipVoice?= null
)

data class Artist(
    val name: String?= null,
    val urls: SocialMediaLinks?= null
)

data class SocialMediaLinks(
    val wiki: String?= null,
    val Pixiv: String?= null,
    val Twitter: String?= null,
    val Link: String?= null
)

data class ShipVoice(
    val name: String?= null,
    val url: String?= null
)

data class Skin(
    val name: String?= null,
    val image: String?= null,
    val background: String?= null,
    val chibi: String?= null,
    val info: SkinInfo?= null
)

data class SkinInfo(
    val live2dModel: Boolean?= null,
    val obtainedFrom: String?= null,
    val category: String?= null,
    val enClient: String?= null,
    val enLimited: String?= null,
    val cnClient: String?= null,
    val cnLimited: String?= null,
    val jpClient: String?= null,
    val jpLimited: String?= null,
    val cost: Int?= null
)

data class ShipGallery(
    val description: String?= null,
    val url: String?= null
)