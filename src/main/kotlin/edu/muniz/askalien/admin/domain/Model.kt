package edu.muniz.askalien.admin.domain

interface Model {
    fun getId(): Int?
    fun populate(sourceObject: Model?)
}