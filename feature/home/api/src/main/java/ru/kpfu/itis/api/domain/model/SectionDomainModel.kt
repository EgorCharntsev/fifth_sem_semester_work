package ru.kpfu.itis.api.domain.model

import ru.kpfu.itis.common.domain.model.SectionType

class SectionDomainModel(
    val sectionType: SectionType,
    val items: List<DomainSearchable>,
)