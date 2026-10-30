# Voxy NeoForge 1.21.1

> **Unofficial NeoForge port** of the Voxy mod

## Special Thanks

**All credit for Voxy goes to [MCRcortex](https://github.com/MCRcortex)**, the original author and creator of this incredible LOD rendering mod.

- **Original Repository:** [MCRcortex/voxy](https://github.com/MCRcortex/voxy)
- **Original Author:** [MCRcortex](https://github.com/MCRcortex)

This repository is a community port to NeoForge 1.21.1, created because the original author has indicated they will not be backporting to this version. We are deeply grateful for MCRcortex's work on Voxy.

## License Notice

The original Voxy mod is licensed under **All Rights Reserved** by MCRcortex. This port is provided for personal use. Please respect the original author's licensing terms.

---

## About

**Voxy** is a Level-of-Detail (LOD) rendering mod for Minecraft that extends your view distance far beyond vanilla limits by rendering distant terrain at lower detail levels.

## Why This Port?

You might wonder: "Why not just use the Fabric version with [Sinytra Connector](https://github.com/Sinytra/Connector)?"

| Aspect | Native NeoForge Port (this repo) | Sinytra Connector |
|--------|----------------------------------|-------------------|
| **Performance** | No translation overhead | Runtime translation layer |
| **Mod Integration** | Native NeoForge API calls | Fabric API emulation via FFAPI |
| **Compatibility** | No Fabric layer to conflict with other mods | Connector/FFAPI can conflict with NeoForge mods |
| **Maintenance** | Must track upstream Voxy changes | Just drop in Fabric jar |
| **Dependencies** | Sodium only | Connector + Forgified Fabric API |

This port is fully native: it uses NeoForge/FML APIs directly (events, `neoforge.mods.toml`,
access transformers) and does **not** need Sinytra Connector or Forgified Fabric API.

## Status

**Beta** - based on the [m3t4f1v3/voxy `mc_1211-sodium0.8.12`](https://github.com/m3t4f1v3/voxy/tree/mc_1211-sodium0.8.12)
1.21.1 backport, ported to NeoForge and updated with fixes from upstream Voxy.

### Features
- LOD terrain rendering beyond vanilla render distance
- Voxy settings page inside Sodium's video settings (also reachable from the NeoForge mod list "Config" button)
- Configurable fog (intensity, density, sky fog distance), cloud distance and SSAO
- Iris shader pack support (Iris 1.8.14+ for Sodium 0.8)
- World curvature for distant LODs (Distant Horizons style, off by default)
- World imports (`/voxy import ...`), including chunks from older Minecraft versions
- Chunky pregeneration is ingested automatically when Chunky is installed
- Vivecraft and Valkyrien Skies compatibility from the 1.21.1 backport

### Not supported on NeoForge
- Nvidium, Flashback and ModMenu integrations (Fabric-only mods)
- FREX flawless frames (Fabric entrypoint)

## Requirements

### Required Dependencies

| Dependency | Version | Link |
|------------|---------|------|
| Minecraft | 1.21.1 | - |
| NeoForge | 21.1.82+ (built against 21.1.265) | [NeoForge](https://neoforged.net/) |
| Sodium | mc1.21.1-0.8.13-neoforge or newer | [Modrinth](https://modrinth.com/mod/sodium/version/mc1.21.1-0.8.13-neoforge) |

### Optional Dependencies

| Dependency | Version | Purpose |
|------------|---------|---------|
| Iris | 1.8.14-beta.1+1.21.1-neoforge | Shader packs with Voxy support |
| Lithium | mc1.21.1-0.15.4-neoforge | General performance |
| Chunky | 1.4.23 (NeoForge) | Pregeneration that feeds Voxy |

## Installation

> **Note:** Due to Voxy's ARR (All Rights Reserved) license, compiled JARs are not distributed. You must build from source.

1. Install NeoForge for Minecraft 1.21.1
2. Install required dependencies (see above)
3. Build Voxy from source (see below)
4. Place the built JAR in your `mods` folder

## Building from Source

```bash
git clone https://github.com/j-shelfwood/voxy-neoforge.git
cd voxy-neoforge
./gradlew build
```

The built JAR will be in `build/libs/`. Building needs JDK 21.

To test in a dev client, `./gradlew runClient` (Sodium is copied into `run/mods` automatically).

## Contributing

For development guidelines, see [CLAUDE.md](CLAUDE.md).

### Validation Scripts

The `scripts/` directory contains build validation tools used in CI.

## Links

- **Original Voxy:** [github.com/MCRcortex/voxy](https://github.com/MCRcortex/voxy)
- **This Port:** [github.com/j-shelfwood/voxy-neoforge](https://github.com/j-shelfwood/voxy-neoforge)
- **1.21.1 backport this port is based on:** [m3t4f1v3/voxy](https://github.com/m3t4f1v3/voxy/tree/mc_1211-sodium0.8.12)
