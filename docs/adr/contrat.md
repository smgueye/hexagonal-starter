# Contrat de Rigueur — Coaching Senior/Staff FAANG

**Coaché :** Seydina · **Coach :** Claude · **Démarrage :** lundi 3 août 2026
**Base de travail :** Projet portfolio DDD & Microservices (Retail/E-commerce)
**Durée d'engagement initial :** 8 semaines (03/08 → 26/09), alignée sur la feuille de route du projet.

---

## 0. Le principe fondateur

Un Staff Engineer de FAANG n'est pas quelqu'un de plus intelligent. C'est quelqu'un dont **les comportements par défaut sont fiables** : il planifie avant de coder, il livre fini, il documente en même temps qu'il produit, et il communique avant qu'on le lui demande. La discipline n'est pas un trait de caractère — c'est un système. Ce document est ton système.

**Règle d'or :** on ne juge jamais l'intention, on juge l'observable. Chaque standard ci-dessous est formulé pour être vérifiable par un tiers (moi) à partir de ton rapport.

---

## 1. Standards de vie non négociables

Ces standards s'appliquent du lundi au vendredi. Ils sont binaires : tenu / non tenu. Pas de « presque ».

| # | Standard | Vérification |
|---|----------|--------------|
| S1 | **Démarrage à heure fixe.** La première session de deep work commence chaque jour à la même heure (à fixer au rapport du J1, puis gravée). Tolérance : 15 min. | Heure de début dans le rapport |
| S2 | **6h de deep work**, découpées en blocs de 90 min max, téléphone hors de la pièce. | Décompte des blocs dans le rapport |
| S3 | **Plan avant exécution.** Aucune ligne de code avant d'avoir écrit les 3 lignes du mini-plan du jour (objectif, livrable, critère de fin). | Mini-plan collé dans le rapport |
| S4 | **Zéro travail inachevé caché.** Tout ce qui n'est pas fini est explicitement listé comme dette du lendemain, jamais passé sous silence. | Section « Reste à faire » du rapport |
| S5 | **Rapport journalier envoyé chaque soir**, même les mauvais jours — *surtout* les mauvais jours. Un jour sans rapport = jour compté à 0. | Réception du rapport |
| S6 | **Arrêt à heure fixe.** Le deep work s'arrête ; le sommeil est un outil de performance, pas une variable d'ajustement. | Heure de fin dans le rapport |

> **Note du coach :** tu dis ne pas être discipliné. C'est précisément pour ça que S1 et S6 existent : la discipline vient des bornes horaires, pas de la volonté. La volonté est réservée au contenu du travail.

---

## 2. Les 4 piliers — comportements attendus

### Pilier 1 — Organisation
- Chaque matin : mini-plan écrit (S3) **avant** d'ouvrir l'IDE.
- Chaque UC du projet DDD est découpé en tâches ≤ 3h avant démarrage. Une tâche > 3h est un signe de découpage raté.
- Le calendrier du projet (§7 du document de référence) est la source de vérité. Tout glissement est annoncé dans le rapport du jour où il est détecté, pas trois jours après.
- Vendredi : 30 min de préparation de la semaine suivante (fait partie des 6h).

### Pilier 2 — Livrer avec qualité et vite
**Definition of Done (DoD) — les 5 critères.** Rien n'est « fini » sans les cinq :
1. Le code compile et tourne (`docker compose up` vert, endpoints health OK).
2. Tests automatisés écrits et verts (au minimum le chemin nominal + 1 cas d'erreur).
3. ADR courte rédigée (pattern retenu, alternative rejetée, pourquoi).
4. Commit(s) poussés avec messages propres (convention type `feat(stock): ...`).
5. Démontrable en 5 min à un tiers sans préparation.

- **Vitesse par le scope, pas par le bâclage :** si le délai est menacé, on coupe du périmètre (et on le dit), on ne coupe jamais dans la DoD.
- Interdiction du « fix de surface » : tout bug corrigé doit être compris (cause racine notée en une phrase dans le rapport).

### Pilier 3 — Documentation
- L'ADR s'écrit **le jour même** de la décision, pas en fin d'UC. Une ADR = 10-15 lignes max : Contexte / Décision / Alternative rejetée / Conséquences.
- Le README de chaque service reste à jour : quelqu'un qui clone doit pouvoir lancer en < 5 min.
- Une trace de mesure (screenshot Grafana, sortie Gatling) est archivée pour chaque affirmation de performance. **Pas de chiffre sans preuve.**

### Pilier 4 — Communication
- Le rapport journalier suit le format PPP (voir §3) — c'est ton standup asynchrone.
- Chaque vendredi, une synthèse hebdo au format STAR pour l'accomplissement principal de la semaine (3-4 phrases) : c'est ton entraînement direct pour les entretiens comportementaux.
- Tout blocage > 2h est signalé dans le rapport avec ce que tu as déjà tenté — jamais un blocage nu.

---

## 3. Template de rapport journalier (à m'envoyer chaque soir)

```
RAPPORT — [date] — Jour [n]

Heures : début [hh:mm] / fin [hh:mm] / blocs deep work : [n] × [durée] = [total]

MINI-PLAN DU MATIN (recopié tel quel)
- Objectif :
- Livrable :
- Critère de fin :

PROGRESS (fait aujourd'hui, avec statut DoD 1-5 pour chaque item)
-

PLANS (demain)
-

PROBLEMS (blocages, avec ce qui a été tenté ; causes racines des bugs)
-

RESTE À FAIRE (dette explicite reportée)
-

STANDARDS : S1 [✓/✗] S2 [✓/✗] S3 [✓/✗] S4 [✓/✗] S6 [✓/✗]

Auto-note du jour (1-5) + une phrase de justification :
```

Mon rôle en retour : je challenge le rapport comme le ferait un EM exigeant — écarts entre plan et réalisé, DoD incomplète maquillée en « fini », blocages mal formulés, glissements non annoncés.

---

## 4. Évaluation hebdomadaire (chaque vendredi soir ou samedi)

Tu m'envoies les 5 rapports + ta synthèse STAR. Je rends une évaluation calibrée sur les échelles FAANG :

| Note | Signification | Critère |
|------|---------------|---------|
| **GE** (Greatly Exceeds) | Semaine de Staff | 5/5 rapports, ≥ 90 % standards tenus, livrable de la semaine 100 % DoD, + une initiative non demandée (mesure, refacto documenté, amélioration du process) |
| **EE** (Exceeds) | Semaine de Senior solide | 5/5 rapports, ≥ 80 % standards, livrable DoD complet |
| **ME** (Meets) | Le contrat est tenu | ≥ 4/5 rapports, ≥ 70 % standards, livrable fonctionnel même si DoD partielle (dette déclarée) |
| **BE** (Below) | Signal d'alerte | Rapports manquants, standards < 70 %, ou dette cachée découverte |
| **NI** (Needs Improvement) | Révision du contrat | Deux semaines BE consécutives → on réduit le périmètre, pas l'exigence |

**Grille par pilier (1-5 chacun) :** Organisation / Livraison / Documentation / Communication — avec pour chaque pilier un point fort et un axe de travail unique pour la semaine suivante. Un seul axe : on ne corrige pas quatre choses à la fois.

---

## 5. Ancrage sur le projet DDD — Semaine 1 (03–07/08)

- **Étape :** J0 — Socle technique.
- **Livrable contractuel de la semaine :** `docker compose up` fonctionnel, 5 squelettes de services, 5 endpoints `/actuator/health` verts, Kafka opérationnel, CI qui build.
- **DoD adaptée au socle :** critères 1, 4, 5 obligatoires ; critère 3 = une ADR sur un choix d'infra (ex. Kind vs Minikube) ; critère 2 = au moins un test de fumée par service.
- **Piège identifié à l'avance :** le socle est l'étape la plus dangereuse pour un profil comme le tien — c'est un puits sans fond de peaufinage (le YAML parfait, le Makefile élégant). Rappel du document de référence : le socle est « minimal ». Le jeudi soir, s'il n'est pas fini, on coupe du périmètre au rapport du jour.

---

## 6. Clause de réalité

Ce contrat sert le projet, pas l'inverse. Si la vie professionnelle (WeVii) ou personnelle percute une journée, le rapport le dit en une ligne et la journée est neutralisée — elle n'est ni un échec ni une excuse. Ce qui rompt le contrat, ce n'est jamais un mauvais jour : c'est le silence.

**Signature d'engagement (à me confirmer par message) :** « Je m'engage sur les 6 standards à partir du 03/08. Mon heure de démarrage est ___ et mon heure de fin est ___. »
