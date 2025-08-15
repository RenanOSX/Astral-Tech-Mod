**Astral Tech Mod**  
*NeoForge • Minecraft 1.21.1*  

---

## 📖 Description  
Astral Tech Mod is a study project aimed at understanding and applying, in practice, the fundamental concepts of mod development in Java for Minecraft 1.21.1 with NeoForge. The focus is on:

- **Client–Server Architecture**  
- **3D Object Rendering**  
- **Event Handling in Java**  

---

## 🚀 Learning Objectives  

1. **Client–Server Architecture**  
   - Understand how the game synchronizes state between server and client.  
   - Study network packets: data serialization and safe information transfer.  
   - Technical concept: *thread‑safe* — ensuring that multiple threads don’t corrupt shared data.

2. **Event Handling**  
   - Use annotations like `@SubscribeEvent` to react to game events (block registration, player clicks, etc.).  
   - Design patterns: *Observer* — components “observe” events without tight coupling.

3. **3D Model Rendering (WIP)**  
   - Configuring custom models via `.obj` files or NeoForge JSON.  
   - Applying textures and UV mapping for correct mesh texturing.  
   - Difference between static rendering (blocks) and dynamic rendering (moving block entities).

4. **Graphical User Interfaces (GUI) (Not implemented yet)**  
   - Building in-game control screens using NeoForge’s native system.  
   - Technical concept: GUI lifecycle — initialization, update, and disposal of visual components.

---

## 🛠 Tools Used  
- **Java 21**  
- **NeoForge 1.21.1**  
- **Gradle**
- **IDE:** IntelliJ IDEA

---

## ⚙️ How to Reproduce  
1. Install Minecraft 1.21.1 with NeoForge 1.21.1.  
2. Clone this repository:  
   ```bash
   git clone https://github.com/renanosx/astral-tech-mod.git
3. Execute commands on Intellij

```bash
./gradlew runData     # Generates JSON files for data-driven assets

./gradlew build       # Compile the project

./gradlew runClient   # Run in client for testing

./gradlew runServer   # Run a local server for testing`
