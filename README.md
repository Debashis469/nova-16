# Nova-16

> A 16-bit computer system built from scratch — from digital logic to operating system.

Nova-16 is a vertically integrated computer architecture project implementing a complete computing stack: custom ISA, CPU microarchitecture, assembler, VM translator, compiler, operating system, and FPGA-based hardware realization.

Inspired by classical RISC design principles and structured compiler construction, Nova-16 explores the full hardware–software boundary in a minimal yet expressive 16-bit architecture.

---

## 🎥 Project Demo

> Replace the image link below with your YouTube thumbnail or GIF.

[![Nova-16 Demo](docs/images/demo-thumbnail.png)](https://your-demo-video-link-here)

🔗 Full Demo Video: https://your-demo-video-link-here

---

## 🧠 System Overview

Nova-16 implements a complete compilation and execution pipeline:

```
High-Level Language (.nova)
        ↓
Compiler
        ↓
VM Code
        ↓
VM Translator
        ↓
Assembly
        ↓
Assembler
        ↓
16-bit Machine Code
        ↓
Nova-16 CPU (Verilog RTL)
        ↓
FPGA Execution
```

The system is designed for architectural clarity, deterministic execution, and hardware–software co-design.

---

## 🏗 Architecture

### High-Level Architecture Diagram

> Add your architecture diagram image here

![Architecture Diagram](docs/images/architecture.png)

---

### CPU Core Components

- 16-bit ALU
- Instruction decoder
- Register set
- Program Counter (PC)
- Control Unit
- Memory interface
- Top-level FPGA integration module

The CPU executes a compact 16-bit instruction format optimized for arithmetic, memory access, and control flow operations.

---

## 🧾 Instruction Set Architecture (ISA)

Nova-16 defines a clean 16-bit instruction format supporting:

- Arithmetic and logical operations
- Memory load/store
- Conditional branching
- Unconditional jumps

The ISA is designed for simplicity while supporting a full compilation pipeline.

Detailed specification available in:

```
docs/isa-specification.md
```

---

## 💻 Software Stack

### 1️⃣ Assembler

Location:
```
/assembler
```

Features:

- Two-pass assembly process
- Symbol resolution
- Label handling
- Binary instruction encoding
- Deterministic machine code generation

---

### 2️⃣ VM Translator

Location:
```
/vm-translator
```

Features:

- Stack-based virtual machine abstraction
- Arithmetic and logical command translation
- Function call handling
- Memory segment management

---

### 3️⃣ Compiler

Location:
```
/compiler
```

Features:

- Tokenization
- Recursive descent parsing
- Symbol table management
- Syntax-directed translation
- VM code generation

---

### 4️⃣ Operating System Layer

Location:
```
/os
```

Provides minimal runtime abstractions:

- Memory utilities
- Basic I/O primitives
- System-level helpers

---

## 🧩 Hardware Implementation

Location:
```
/hardware
```

Implemented in Verilog RTL.

### Key Modules

- ALU
- Control Unit
- CPU Core
- Memory Interface
- Top-level FPGA module
- FPGA constraints (.xdc)

Vivado-generated artifacts are excluded from version control.

---

## 🖥 FPGA Demonstration

> Add FPGA execution video thumbnail here

[![FPGA Demo](docs/images/fpga-thumbnail.png)](https://your-fpga-video-link)

---

## 📁 Project Structure

```
nova-16/
│
├── assembler/
├── compiler/
├── vm-translator/
├── os/
├── hardware/
│
├── docs/
│   ├── images/
│   ├── isa-specification.md
│   └── architecture-notes.md
│
├── .gitignore
└── README.md
```

---

## 🛠 Build & Usage

### Assemble a Program

```
cd assembler
javac *.java
java NovaAssembler program.asm
```

---

### Translate VM Code

```
cd vm-translator
javac *.java
java VMTranslator program.vm
```

---

### Compile High-Level Code

```
cd compiler
javac *.java
java Compiler Main.nova
```

---

## 🧪 Testing

- Assembly-level tests
- VM-level test programs
- End-to-end compilation validation
- FPGA synthesis validation

Testing ensures correctness across translation layers and instruction execution.

---

## 🎯 Design Principles

- Minimal 16-bit architecture
- Clear separation of hardware and software layers
- Deterministic compilation pipeline
- FPGA-synthesizable RTL
- Architectural transparency

---

## 🔮 Future Enhancements

- Pipelined CPU variant
- Interrupt support
- Expanded ISA
- Performance benchmarking
- Formal instruction verification

---

## 📚 Inspiration

Nova-16 draws conceptual inspiration from classical reduced instruction set architectures, stack-based virtual machines, and structured compiler design methodologies. The focus is on architectural clarity and full-stack computing implementation.

---

## 📄 License

Released for educational and research exploration.
