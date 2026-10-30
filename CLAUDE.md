# Voxy NeoForge 1.21.1 Port - Project Context

## Project Overview

This is a **NeoForge 1.21.1 port** of the Voxy mod (originally Fabric).

**Target Platform**: NeoForge 21.1.x for Minecraft 1.21.1
**Source**: [m3t4f1v3/voxy `mc_1211-sodium0.8.12`](https://github.com/m3t4f1v3/voxy/tree/mc_1211-sodium0.8.12) (Fabric 1.21.1 backport of Voxy), plus selected upstream MCRcortex/voxy fixes
**Goal**: Full compatibility with NeoForge modding ecosystem, as a native port (no Sinytra Connector / Forgified Fabric API)

## Critical Development Rules

### 1. Reference-First Research

**ALWAYS** research cloned GitHub repositories before making changes:

```
.reference/
├── minecraft/1.21.1/decompiled/    # MC 1.21.1 decompiled sources
├── sodium/0.8.13/                   # Sodium bytecode/sources
└── [other reference repos]
```

**Process**:
1. Identify the issue (mixin signature, API change, etc.)
2. Search `.reference/` repositories for actual source code
3. Verify method signatures, class structures, field types
4. Apply fix based on **verified evidence**, not assumptions
5. Document evidence in commit/comments

**NO GUESSING** - Every change must be backed by source code verification.

### 2. Validation Before Changes

Before modifying any file:
1. Read the current implementation
2. Find corresponding reference in `.reference/` repos
3. Verify the proposed change matches the reference
4. Test build after changes

### 3. Dependency Versions

**Current validated versions** (for NeoForge 1.21.1):
- NeoForge: 21.1.265
- Minecraft: 1.21.1
- Sodium: mc1.21.1-0.8.13-neoforge (compile against `net.caffeinemc:sodium-neoforge-mod` from maven.caffeinemc.net, the Modrinth jar is a JarJar container)
- Iris (optional): 1.8.14-beta.1+1.21.1-neoforge
- Lithium (optional): mc1.21.1-0.15.4-neoforge
- Chunky (optional): NeoForge 1.4.23, Modrinth version id `LuFhm4eU` (the plain version number resolves to the Forge build)

**IMPORTANT**: Verify all dependency versions against actual releases on Modrinth/CurseForge for NeoForge 1.21.1 specifically.

## Key Files

### Build Configuration
- `build.gradle` - Dependencies, build settings
- `gradle.properties` - Version numbers
- `src/main/resources/META-INF/neoforge.mods.toml` - Mod metadata and dependencies
- `src/main/resources/META-INF/accesstransformer.cfg` - Access transformers

### Mixin Configurations
- `src/main/resources/client.voxy.mixins.json` - Client-side mixins
- `src/main/resources/common.voxy.mixins.json` - Common mixins

## Porting Considerations

### Fabric → NeoForge Differences
1. **Mod metadata**: fabric.mod.json → neoforge.mods.toml
2. **Access wideners**: .accesswidener → accesstransformer.cfg
3. **Dependencies**: Must be explicitly declared in neoforge.mods.toml
4. **Entrypoints**: Fabric entrypoints don't work on NeoForge
5. **Mixin remapping**: Different obfuscation system

### Platform layer (no Fabric API)
- Never depend on Forgified Fabric API or Sinytra Connector; use NeoForge/FML APIs directly
- `commonImpl.Platform` replaces `FabricLoader` (uses `LoadingModList`, which works in mixin plugins before `ModList` exists)
- `VoxyClient` is the client `@Mod` entrypoint; commands go through `RegisterClientCommandsEvent`
- Sodium finds `VoxyConfigMenu` through its `@ConfigEntryPointForge("voxy")` annotation, do not also declare a `sodium:config_api_user` mod property (duplicate registration crashes)
- Other mods' resources (e.g. Sodium shader includes) are not visible to voxy's class loader, load them through that mod
- ATs cannot target record component fields; widen the record (and its constructor) and use the accessors

## Validation Workflow

1. **Extract reference sources** to `.reference/` directory
2. **Validate mixin signatures** against decompiled MC/mod sources
3. **Validate Access Transformers** against actual class structures
4. **Test build** after every significant change
5. **Document fixes** with evidence from reference sources

## Testing

- `./gradlew runClient` launches a dev client with Sodium in `run/mods`; join a world to exercise mixins, since many only apply when their target class loads
- Build with JDK 21 (`JAVA_HOME=/usr/lib/jvm/java-21-openjdk` if the default java is older)

## Known Issues (NeoForge Port)

Track porting-specific issues here. Pre-existing bugs in the original Fabric mod are out of scope.
