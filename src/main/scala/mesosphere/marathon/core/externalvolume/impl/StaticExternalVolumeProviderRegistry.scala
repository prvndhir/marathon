package mesosphere.marathon
package core.externalvolume.impl

import mesosphere.marathon.core.externalvolume.impl.providers.DVDIProvider

/**
  * StaticExternalVolumeProviderRegistry is a fixed, precomputed storage provider registry
  */
protected[externalvolume] object StaticExternalVolumeProviderRegistry extends ExternalVolumeProviderRegistry {
  def make(prov: ExternalVolumeProvider*): Map[String, ExternalVolumeProvider] =
    prov.map(p => p.name -> p)(collection.breakOut)

  val registry = make(
    // list supported providers here; all MUST provide a non-empty "name" trait
    DVDIProvider
  )

  override def get(name: String): Option[ExternalVolumeProvider] = registry.get(name)

  override def all: Seq[ExternalVolumeProvider] = registry.values.to[Seq]
}
